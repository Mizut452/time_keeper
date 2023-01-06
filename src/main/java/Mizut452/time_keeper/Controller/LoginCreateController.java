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
        loginUser.setMailAddress(getMailAddress);
        loginUser.setUsername(getUserName);
        loginUser.setPassword(getPassWord);
        loginUser.setRoleName(getRoleName);

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
        timekeepUpdateReq.setTimeKeepId(getTimeKeepId);
        timekeepUpdateReq.setSubject(getSubject);
        timekeepUpdateReq.setContext(getContext);
        timekeepUpdateReq.setTotalTime(getTotalTime);
        timekeepUpdateReq.setWhatDate(getWhatDate);
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
        timekeepUpdateReq.setTimeKeepId(getTimeKeepId);
        timekeepUpdateReq.setSubject(getSubject);
        timekeepUpdateReq.setContext(getContext);
        timekeepUpdateReq.setTotalTime(getTotalTime);
        timekeepUpdateReq.setWhatDate(getWhatDate);

        model.addAttribute("timeList", timekeepUpdateReq);
        return mav;
    }

    @RequestMapping("/add")
    public String addItem(@AuthenticationPrincipal LoginUser loginUser,
                          @ModelAttribute Timekeep timekeep,
                          @ModelAttribute String username) {
        timekeep.setSubject(getSubject);
        timekeep.setContext(getContext);
        timekeep.setTotalTime(getTotalTime);
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

    Timekeep timekeep;
    LoginUser loginUser;

    //LoginUserのgetter
    String getMailAddress = loginUser.getMailAddress();
    String getUserName = loginUser.getUsername();
    String getPassWord = loginUser.getPassword();
    String getRoleName = loginUser.getRoleName();

    //Timekeepのgetter
    int getTimeKeepId = timekeep.getTimeKeepId();
    String getTimekeep_userName = timekeep.getUsername();
    String getSubject = timekeep.getSubject();
    String getContext = timekeep.getContext();
    String getTotalTime = timekeep.getTotalTime();
    String getWhatDate = timekeep.getWhatDate();
}
