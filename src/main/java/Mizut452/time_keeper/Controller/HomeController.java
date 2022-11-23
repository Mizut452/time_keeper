package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.LoginSecurity.LoginUserDetails;
import Mizut452.time_keeper.LoginSecurity.LoginUserDetailsService;
import Mizut452.time_keeper.LoginSecurity.LoginUserRepository;
import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.Timekeep;
import Mizut452.time_keeper.Service.AddTimeKeepService;
import Mizut452.time_keeper.Service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
public class HomeController {
    private LoginUserRepository repo;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    private CreateAccountService createAccountservice;

    @Autowired
    private AddTimeKeepService addTimekeepservice;

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Autowired
    private TimekeepMapper timekeepMapper;

    //public record UserList(String mailaddress, String username, String password, List<String> roleList) {}
    //private List<UserList> userlists = new ArrayList<>();


    @GetMapping("/")
    public ModelAndView home(ModelAndView mav) {
        mav = new ModelAndView("home");
        mav.addObject("TimeList", timekeepMapper.selectAll());
        return mav;
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @RequestMapping("/mypage/{username}")
    public Object userPage(ModelAndView mav,
                           LoginUser loginuser,
                           @PathVariable("username") String username,
                           Authentication auth2) {
        LoginUser record = loginUserMapper.selectUsername(username);
        auth2 = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth2.getPrincipal();
        //String principalUsernameは、ログインしている人のIDを表す
        String principalUsername = principal.getUsername();
        LoginUser loginUser1 = new LoginUser();
        loginUser1.setUsername(username);
        String myname = loginUser1.getUsername();

        if (record == null) {
            return "NullAccount";
        } else if (myname.equals(principalUsername)) {
            return "PrincipalUserPage";
        } else {
            mav = new ModelAndView("userpage");
            mav.addObject("TimeList", timekeepMapper.selectAll());
            return mav;
        }
    }

    @GetMapping("/createaccount")
    public String createAccount() {
        return "createAccount";
    }

    @GetMapping("/userlist")
    public ModelAndView userlistPage(ModelAndView mav) {
        mav = new ModelAndView("UserList");
        mav.addObject("UserList", loginUserMapper.selectAll());
        return mav;
    }

    @RequestMapping("/create")
    public String addAccount(@ModelAttribute LoginUser loginUser) {
        loginUser.setMailaddress(loginUser.getMailaddress());
        loginUser.setUsername(loginUser.getUsername());
        loginUser.setPassword(loginUser.getPassword());
        loginUser.setRoleName(loginUser.getRoleName());

        createAccountservice.createAccount(loginUser);

        return "Confirm";
    }

    @RequestMapping("/add")
    public String addItem(Authentication auth1,
                          @ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username){
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setTotalTime(timekeep.getTotalTime());
        auth1 = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth1.getPrincipal();
        username = principal.getUsername();

        addTimekeepservice.addTimekeep(timekeep);
        return "redirect:/mypage/" + username;
    }

    /*@RequestMapping("/add")
    public String addItem(@AuthenticationPrincipal LoginUserDetails details,
                          @RequestParam("subject") String subject,
                          @RequestParam("context") String context,
                          @RequestParam("totalTime") String totalTime) {
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String username = principal.getUsername();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter change = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        String Wdate = now.format(change);
        return null;

    }*/


}
