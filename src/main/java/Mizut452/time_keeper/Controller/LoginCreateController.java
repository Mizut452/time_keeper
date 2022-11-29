package Mizut452.time_keeper.Controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/mypage/{username}/updatePage/{timekeepid}")
    public Object updateTimeList(ModelAndView mav,
                                 @PathVariable("username") String username,
                                 @PathVariable("timekeepid") int timekeepid,
                                 Model model) {
        mav = new ModelAndView("UpdateTimeListItem");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        mav.addObject("timeList", timekeepMapper.selectUpdateItem(timekeepid));
        return mav;
    }

    @RequestMapping("/add")
    public String addItem(@ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username) {
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
    public String updateItems(@ModelAttribute String username,
                             @ModelAttribute Timekeep timekeep) {
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setTotalTime(timekeep.getTotalTime());
        timekeep.setWdate(timekeep.getWdate());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        username = principal.getUsername();

        addTimekeepservice.updateTimekeep(timekeep);

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

    @Autowired
    TimekeepMapper timekeepMapper;
}
