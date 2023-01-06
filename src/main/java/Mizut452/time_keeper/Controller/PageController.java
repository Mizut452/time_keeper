package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.LoginSecurity.LoginUserDetailsService;
import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    public PageController(LoginUserDetailsService loginUserDetailsService) {
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @GetMapping("/test")
    public String testPage() {
        return "TestPage";
    }

    @GetMapping("/")
    public Object home(@AuthenticationPrincipal LoginUser loginUser) {

        if (loginUser == null) {
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("TimeList", timekeepMapper.selectAll());
            return mav;
        } else {
            String PrincipalUserName = loginUser.getUsername();
            ModelAndView mav = new ModelAndView("home");
            mav.addObject("TimeList", timekeepMapper.selectAll());
            mav.addObject("LoginList", PrincipalUserName);
            return mav;
        }
    }

    @RequestMapping("/mypage/{username}")
    public Object userPage(ModelAndView mav,
                           @PathVariable("username") String username,
                           @AuthenticationPrincipal LoginUser loginUser) {

        if(loginUser == null) {
            mav = new ModelAndView("userpage");
            mav.addObject("TimeList", timekeepMapper.principalSelectAll(username));
            return mav;
        } else {
            LoginUser record = loginUserMapper.findByUsername(username);
            String Username = loginUser.getUsername();


            if (record == null) {
                return "NullAccount";
            } else if (username.equals(Username)) {
                mav = new ModelAndView("PrincipalUserPage");
                mav.addObject("LoginList", Username);
                mav.addObject("PrincipalTimeList", timekeepMapper.principalSelectAll(username));
                return mav;
            } else {
                mav = new ModelAndView("userpage");

                mav.addObject("TimeList", timekeepMapper.principalSelectAll(username));
                return mav;
            }
        }

    }

    @GetMapping("/userlist")
    public ModelAndView userlistPage(@AuthenticationPrincipal LoginUser loginUser) {
        ModelAndView mav = new ModelAndView("UserList");
        mav.addObject("UserList", loginUserMapper.selectAll());
        String PrincipalUserName = loginUser.getUsername();
        mav.addObject("TimeList", PrincipalUserName);
        return mav;
    }

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private TimekeepMapper timekeepMapper;

    private final LoginUserDetailsService loginUserDetailsService;
}