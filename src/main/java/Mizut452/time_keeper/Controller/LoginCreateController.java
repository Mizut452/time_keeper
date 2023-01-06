package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.TimekeepMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.Timekeep;
import Mizut452.time_keeper.Model.Entity.TimekeepUpdateReq;
import Mizut452.time_keeper.Service.AddTimeKeepService;
import Mizut452.time_keeper.Service.CreateAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        loginUser.setMailAddress(loginUser.getMailAddress());
        loginUser.setUsername(loginUser.getUsername());
        loginUser.setPassword(loginUser.getPassword());
        loginUser.setRoleName(loginUser.getRoleName());

        createAccountservice.createAccount(loginUser);

        return "Confirm";
    }


    @RequestMapping("/mypage/{username}/updatePage/{timeKeepId}")
    public Object updateTimeList(ModelAndView mav,
                                 @PathVariable("username") String username,
                                 @PathVariable("timeKeepId") int timeKeepId,
                                 Model model) {
        mav = new ModelAndView("UpdateTimeListItem");
        Timekeep timekeep = addTimekeepservice.findById(timeKeepId);
        TimekeepUpdateReq timekeepUpdateReq = new TimekeepUpdateReq();
        timekeepUpdateReq.setTimeKeepId(timekeep.getTimeKeepId());
        timekeepUpdateReq.setSubject(timekeep.getSubject());
        timekeepUpdateReq.setContext(timekeep.getContext());
        timekeepUpdateReq.setHours(timekeep.getHours());
        timekeepUpdateReq.setMinutes(timekeep.getMinutes());
        timekeepUpdateReq.setWhatDate(timekeep.getWhatDate());
        model.addAttribute("timeList", timekeepUpdateReq);
        return mav;
    }

    @RequestMapping("/mypage/{username}/delete/{timeKeepId}")
    public Object deleteTimeList(ModelAndView mav,
                                 @PathVariable("username") String username,
                                 @PathVariable("timeKeepId") int timeKeepId,
                                 Model model) {
        mav = new ModelAndView("deleteTimeListItem");
        Timekeep timekeep = addTimekeepservice.findById(timeKeepId);
        TimekeepUpdateReq timekeepUpdateReq = new TimekeepUpdateReq();
        timekeepUpdateReq.setTimeKeepId(timekeep.getTimeKeepId());
        timekeepUpdateReq.setSubject(timekeep.getSubject());
        timekeepUpdateReq.setContext(timekeep.getContext());
        timekeepUpdateReq.setHours(timekeep.getHours());
        timekeepUpdateReq.setMinutes(timekeep.getMinutes());
        timekeepUpdateReq.setWhatDate(timekeep.getWhatDate());

        model.addAttribute("timeList", timekeepUpdateReq);
        return mav;
    }

    @RequestMapping("/add")
    public String addItem(@AuthenticationPrincipal LoginUser loginUser,
                          @ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username) {
        timekeep.setSubject(timekeep.getSubject());
        timekeep.setContext(timekeep.getContext());
        timekeep.setHours(timekeep.getHours());
        timekeep.setMinutes(timekeep.getMinutes());
        username = loginUser.getUsername();

        addTimekeepservice.addTimekeep(timekeep);
        return "redirect:/mypage/" + username;
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateItems(@ModelAttribute String username,
                             @AuthenticationPrincipal LoginUser loginUser,
                             @ModelAttribute TimekeepUpdateReq timekeepUpdateReq) {
        username = loginUser.getUsername();

        addTimekeepservice.update(timekeepUpdateReq);

        return "redirect:/mypage/" + username;
    }

    @RequestMapping("/delete")
    public String deleteItem(@ModelAttribute TimekeepUpdateReq timekeepUpdateReq,
                             @AuthenticationPrincipal LoginUser loginUser) {
        String username = loginUser.getUsername();

        int timeKeepId = timekeepUpdateReq.getTimeKeepId();

        addTimekeepservice.delete(timeKeepId);
        return "redirect:/mypage/" + username;
    }

    @Autowired
    private CreateAccountService createAccountservice;

    @Autowired
    private AddTimeKeepService addTimekeepservice;

    @Autowired
    TimekeepMapper timekeepMapper;
}