package Mizut452.time_keeper.Controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;

@Controller
public class LoginCreateController {

    @GetMapping("/login")
    public String login() {

        return "Login";
    }

    @GetMapping("/createaccount")
    public String createAccount() {
        return "createAccount";
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
    public String addItem(@ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username){
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setTotalTime(timekeep.getTotalTime());
        Authentication auth1 = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth1.getPrincipal();
        username = principal.getUsername();

        addTimekeepservice.addTimekeep(timekeep);
        return "redirect:/mypage/" + username;
    }

    @RequestMapping("/update")
    public String updateItem(@ModelAttribute String username,
                             @ModelAttribute Timekeep timekeep) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        username = principal.getUsername();

        return "redirect:/mypage/" + username;
    }

    @RequestMapping("/delete")
    public String deleteItem() {
        return null;
    }

    @Autowired
    private CreateAccountService createAccountservice;

    @Autowired
    private AddTimeKeepService addTimekeepservice;
}
