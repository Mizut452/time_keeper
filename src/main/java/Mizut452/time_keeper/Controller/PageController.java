package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.Timekeep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class PageController {
    @GetMapping("/")
    public Object home(@AuthenticationPrincipal LoginUser loginUser) {
        if (loginUser.getUsername() == null) {
            return "home";
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String PU = principal.getUsername();
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("TimeList", timekeepMapper.selectAll());
            mav.addObject("LoginList", PU);
            return mav;
        }
    }

    @RequestMapping("/mypage/{username}")
    public Object userPage(ModelAndView mav,
                           LoginUser loginuser,
                           @PathVariable("username") String username) {
        LoginUser record = loginUserMapper.selectUsername(username);
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth2.getPrincipal();
        //String principalUsernameは、ログインしている人のIDを表す
        String principalUsername = principal.getUsername();
        LoginUser loginUser1 = new LoginUser();
        loginUser1.setUsername(username);
        String myname = loginUser1.getUsername();

        if (record == null) {
            return "NullAccount";
        } else if (myname.equals(principalUsername)) {
            mav = new ModelAndView("PrincipalUserPage");
            mav.addObject("PrincipalTimeList", timekeepMapper.principalSelectAll(username));
            return mav;
        } else {
            mav = new ModelAndView("userpage");

            mav.addObject("TimeList", timekeepMapper.principalSelectAll(username));
            return mav;
        }
    }

    @GetMapping("/userlist")
    public ModelAndView userlistPage() {
        ModelAndView mav = new ModelAndView("UserList");
        mav.addObject("UserList", loginUserMapper.selectAll());
        return mav;
    }

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private TimekeepMapper timekeepMapper;

}
