package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.CompanyDetailMapper;
import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyDetailUpdateReq;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import Mizut452.time_keeper.Model.Entity.CompanyListUpdateReq;
import Mizut452.time_keeper.Service.CompanyDetailService;
import Mizut452.time_keeper.Service.CompanyListService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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

@Controller
public class JobHuntingToolController {

    @RequestMapping("/company_add")
    public String addCompanyItem(@ModelAttribute CompanyList companyList,
                                 CompanyDetail companyDetail,
                                 String companyName,
                                 Model model) {
        companyList.setCompanyName(companyList.getCompanyName());
        companyList.setIndustry(companyList.getIndustry());
        companyList.setHeadlocate(companyList.getHeadlocate());
        companyList.setCompanyURL(companyList.getCompanyURL());
        companyList.setCompanyLother(companyList.getCompanyLother());

        companyListService.addCompanyList(companyList);
        companyName = companyList.getCompanyName();
        model.addAttribute("CompanyList", companyListMapper.selectAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String PrincipalUserName = principal.getUsername();
        model.addAttribute("TimeList", PrincipalUserName);
        model.addAttribute("CompanyDetail", companyDetailMapper.selectAll());


        return "JobHuntingTool";
    }

    @GetMapping("/jobHuntingTool")
    public ModelAndView JobHuntPage() {
        ModelAndView mav = new ModelAndView("JobHuntingTool");
        mav.addObject("CompanyList", companyListMapper.selectAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String PrincipalUserName = principal.getUsername();
        mav.addObject("TimeList", PrincipalUserName);
        mav.addObject("CompanyDetail", companyDetailMapper.selectAll());
        return mav;
    }

    @GetMapping("/jobHuntingTool/{companyName}")
    public Object JobCompanyPage(@ModelAttribute CompanyList companyList,
                                 @PathVariable String companyName,
                                 CompanyDetail companyDetail,
                                 ModelAndView mav) {
        CompanyList record = companyListMapper.findByCompanyName(companyName);

        if(record == null) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String PrincipalUserName = principal.getUsername();
            mav.addObject("TimeList", PrincipalUserName);
            return "NullCompany";
        } else {
            mav = new ModelAndView("JobHuntingCompany");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails principal = (UserDetails) authentication.getPrincipal();
            String PrincipalUserName = principal.getUsername();
            mav.addObject("TimeList", PrincipalUserName);
            companyDetail.setCompany_treatment(companyDetail.getCompany_treatment());
            companyDetail.setCompany_welfare(companyDetail.getCompany_welfare());
            companyDetail.setCompany_another(companyDetail.getCompany_another());
            companyDetail.setCompany_strongPoint(companyDetail.getCompany_strongPoint());
            companyDetail.setCompany_weakPoint(companyDetail.getCompany_weakPoint());
            companyDetail.setCompany_whatJob(companyDetail.getCompany_whatJob());
            companyDetail.setCompany_flow(companyDetail.getCompany_flow());
            companyDetailService.addCompanyDetail(companyDetail);
            companyDetailService.addIdandNameService(companyName);
            mav.addObject("companyName", companyName);
            mav.addObject("CompanyDetail", companyDetailMapper.selectACompany(companyName));
            return mav;
        }
    }

    @RequestMapping("/addComDetail")
    public Object companyDetails(@ModelAttribute CompanyDetail companyDetail,
                                 String companyName,
                                 ModelAndView mav) {
        mav = new ModelAndView("JobHuntingCompany");
        companyDetail.setCompany_whatJob(companyDetail.getCompany_whatJob());
        companyDetail.setCompany_strongPoint(companyDetail.getCompany_strongPoint());
        companyDetail.setCompany_weakPoint(companyDetail.getCompany_weakPoint());
        companyDetail.setCompany_treatment(companyDetail.getCompany_treatment());
        companyDetail.setCompany_welfare(companyDetail.getCompany_welfare());
        companyDetail.setCompany_flow(companyDetail.getCompany_flow());
        companyDetail.setCompany_another(companyDetail.getCompany_another());
        companyDetailService.addCompanyDetail(companyDetail);
        companyName = companyDetail.getCompany_whatJob();
        mav.addObject("CompanyDetail", companyDetailMapper.selectAll());
        return "redirect:/jobHuntingTool/" + companyName;
    }

    @GetMapping("/jobHuntingTool/{companyName}/edit")
    public Object companyEditPage(@PathVariable("companyName") String companyName,
                                  ModelAndView mav,
                                  Model model) {
        mav = new ModelAndView("companyEditPage");
        CompanyList companyList = companyListService.findByCompanyName(companyName);
        CompanyListUpdateReq companyListUpdateReq = new CompanyListUpdateReq();
        companyListUpdateReq.setId(companyList.getId());
        companyListUpdateReq.setCompanyName(companyList.getCompanyName());
        companyListUpdateReq.setHeadlocate(companyList.getHeadlocate());
        companyListUpdateReq.setIndustry(companyList.getIndustry());
        companyListUpdateReq.setCompanyURL(companyList.getCompanyURL());
        companyListUpdateReq.setCompanyLother(companyList.getCompanyLother());
        companyListUpdateReq.setAreOsaka(companyList.isAreOsaka());
        model.addAttribute("companyList", companyListUpdateReq);

        CompanyDetail companyDetail = companyDetailService.findByCompanyName(companyName);
        CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
        companyDetailUpdateReq.setCompany_weakPoint(companyDetail.getCompany_weakPoint());
        companyDetailUpdateReq.setCompany_strongPoint(companyDetail.getCompany_strongPoint());
        companyDetailUpdateReq.setCompany_another(companyDetail.getCompany_another());
        companyDetailUpdateReq.setCompany_flow(companyDetail.getCompany_flow());
        companyDetailUpdateReq.setCompany_treatment(companyDetail.getCompany_treatment());
        companyDetailUpdateReq.setCompany_welfare(companyDetail.getCompany_welfare());
        companyDetailUpdateReq.setCompanyDetail_Cname(companyDetail.getCompany_whatJob());
        companyDetailUpdateReq.setCompanyDetail_Cname(companyDetail.getCompanyDetail_Cname());
        companyDetailUpdateReq.setCompanyDetail_id(companyDetail.getCompanyDetail_id());
        model.addAttribute("companyDetail", companyDetailUpdateReq);

        return mav;
    }

    @RequestMapping("/jobHuntingTool/update")
    public String updateCompany(@ModelAttribute CompanyListUpdateReq companyListUpdateReq) {

        companyListService.update(companyListUpdateReq);

        return "redirect:/jobHuntingTool";
    }

    @Autowired
    private CompanyListMapper companyListMapper;

    @Autowired
    private CompanyDetailMapper companyDetailMapper;

    @Autowired
    private CompanyListService companyListService;

    @Autowired
    private CompanyDetailService companyDetailService;

}
