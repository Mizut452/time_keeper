package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.LoginSecurity.LoginUserRepository;
import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.Timekeep;
import Mizut452.time_keeper.Service.AddTimeKeepService;
import Mizut452.time_keeper.Service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
                           @PathVariable("username")String username, Model model) {
        LoginUser record = loginUserMapper.selectUsername(username);
        if(record == null) {
            return "NullAccount";
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
    public String addItem(Authentication auth,
                          @ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username){
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setTotalTime(timekeep.getTotalTime());
        auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
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
