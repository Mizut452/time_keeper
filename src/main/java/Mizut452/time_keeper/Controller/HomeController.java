package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.LoginSecurity.LoginUserDetails;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public String userPage(@PathVariable("username")String username,Model model) {
        LoginUser record = loginUserMapper.selectUsername(username);
        if(record == null) {
            return "NullAccount";
        } else {
            return "userpage";
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
    public String addItem(@ModelAttribute Timekeep timekeep) {
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setTotalTime(timekeep.getTotalTime());

        addTimekeepservice.addTimekeep(timekeep);
        return "redirect://home";
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
