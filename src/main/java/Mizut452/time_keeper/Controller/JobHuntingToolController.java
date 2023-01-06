package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.CompanyDetailMapper;
import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.*;
import Mizut452.time_keeper.Service.CompanyDetailService;
import Mizut452.time_keeper.Service.CompanyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String addCompanyItem(@AuthenticationPrincipal LoginUser loginUser,
                                 CompanyList companyList,
                                 CompanyDetail companyDetail,
                                 Model model) {
        companyList.setCompanyName(companyList.getCompanyName());
        companyList.setIndustry(companyList.getIndustry());
        companyList.setHeadLocation(companyList.getHeadLocation());
        companyList.setCompanyURL(companyList.getCompanyURL());
        companyList.setCompanyListOther(companyList.getCompanyListOther());
        companyDetail.setCompanyDetail_CompanyName(companyList.getCompanyName());
        companyDetail.setCompanyDetail_id(companyList.getId());

        companyListService.addCompanyList(companyList);
        companyDetailService.addCompanyDetail(companyDetail);

        model.addAttribute("CompanyList", companyListMapper.selectAll());
        String PrincipalUserName = loginUser.getUsername();
        model.addAttribute("TimeList", PrincipalUserName);
        model.addAttribute("CompanyDetail", companyDetailMapper.selectAll());


        return "JobHuntingTool";
    }

    @GetMapping("/jobHuntingTool")
    public ModelAndView JobHuntPage(@AuthenticationPrincipal LoginUser loginUser) {
        ModelAndView mav = new ModelAndView("JobHuntingTool");
        mav.addObject("CompanyList", companyListMapper.selectAll());
        String PrincipalUserName = loginUser.getUsername();
        mav.addObject("TimeList", PrincipalUserName);
        mav.addObject("CompanyDetail", companyDetailMapper.selectAll());
        return mav;
    }

    @GetMapping("/jobHuntingTool/{companyName}")
    public Object JobCompanyPage(@ModelAttribute CompanyList companyList,
                                 @PathVariable String companyName,
                                 @AuthenticationPrincipal LoginUser loginUser,
                                 ModelAndView mav) {
        String PrincipalUserName = loginUser.getUsername();
        CompanyList record = companyListMapper.findByCompanyName(companyName);

        if(record == null) {
            mav.addObject("TimeList", PrincipalUserName);
            return "NullCompany";
        } else {
            mav = new ModelAndView("JobHuntingCompany");
            CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
            companyDetailUpdateReq.setCompanyDetail_id(companyDetail.getCompanyDetail_id());
            int id = companyDetailUpdateReq.getCompanyDetail_id();
            mav.addObject("id", id);
            mav.addObject("TimeList", PrincipalUserName);
            mav.addObject("companyName", companyName);
            mav.addObject("CompanyDetail", companyDetailMapper.selectACompany(companyName));
            return mav;
        }
    }

    @GetMapping("/jobHuntingTool/{companyName}/edit")
    public Object companyEditPage(@PathVariable("companyName") String companyName,
                                  Model model) {
        ModelAndView mav = new ModelAndView("companyEditPage");
        CompanyListUpdateReq companyListUpdateReq = new CompanyListUpdateReq();
        companyListUpdateReq.setId(companyList.getId());
        companyListUpdateReq.setCompanyName(companyList.getCompanyName());
        companyListUpdateReq.setHeadlocate(companyList.getHeadLocation());
        companyListUpdateReq.setIndustry(companyList.getIndustry());
        companyListUpdateReq.setCompanyURL(companyList.getCompanyURL());
        companyListUpdateReq.setCompanyLother(companyList.getCompanyListOther());
        companyListUpdateReq.setAreOsaka(companyList.isAreOsaka());
        model.addAttribute("companyList", companyListUpdateReq);

        CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
        companyDetailUpdateReq.setCompany_weakPoint(companyDetail.getCompany_weakPoint());
        companyDetailUpdateReq.setCompany_strongPoint(companyDetail.getCompany_strongPoint());
        companyDetailUpdateReq.setCompany_another(companyDetail.getCompany_another());
        companyDetailUpdateReq.setCompany_flow(companyDetail.getCompany_flow());
        companyDetailUpdateReq.setCompany_treatment(companyDetail.getCompany_treatment());
        companyDetailUpdateReq.setCompany_welfare(companyDetail.getCompany_welfare());
        companyDetailUpdateReq.setCompany_whatJob(companyDetail.getCompany_whatJob());
        companyDetailUpdateReq.setCompanyDetail_Cname(companyDetail.getCompanyDetail_CompanyName());
        companyDetailUpdateReq.setCompanyDetail_id(companyDetail.getCompanyDetail_id());
        model.addAttribute("companyDetail", companyDetailUpdateReq);

        return mav;
    }

    @RequestMapping("/jobHuntingTool/update")
    public String updateCompany(CompanyListUpdateReq companyListUpdateReq) {
        companyListService.update(companyListUpdateReq);
        return "redirect:/jobHuntingTool";
    }

    @RequestMapping("/addComDetail")
    public String companyDetails(CompanyDetailUpdateReq companyDetailUpdateReq) {
        companyDetailService.update(companyDetailUpdateReq);
        return "redirect:/jobHuntingTool/";
    }

    @RequestMapping("/JobHuntingTool/deleteCompanyList")
    public Object deleteCompany(@ModelAttribute CompanyDetailUpdateReq companyDetailUpdateReq,
                                Model model,
                                ModelAndView mav) {
        String companyName = companyDetailUpdateReq.getCompanyDetail_Cname();
        companyListService.deleteCompanyList(companyName);
        companyDetailService.deleteCompanyDetail(companyName);
        return "redirect:/jobHuntingTool";
    }

    @RequestMapping("JobHuntingTool/deleteCompanyList/{id}/confirm")
    public Object deleteCompanyConfirm(@PathVariable("id") int id,
                                       Model model) {
        ModelAndView mav = new ModelAndView("Confirm");
        CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
        companyDetailUpdateReq.setCompany_another(companyDetail.getCompany_another());
        companyDetailUpdateReq.setCompany_treatment(companyDetail.getCompany_treatment());
        companyDetailUpdateReq.setCompany_welfare(companyDetail.getCompany_welfare());
        companyDetailUpdateReq.setCompany_flow(companyDetail.getCompany_flow());
        companyDetailUpdateReq.setCompanyDetail_Cname(companyDetail.getCompanyDetail_CompanyName());
        companyDetailUpdateReq.setCompany_strongPoint(companyDetail.getCompany_strongPoint());
        companyDetailUpdateReq.setCompany_weakPoint(companyDetail.getCompany_weakPoint());
        companyDetailUpdateReq.setCompany_whatJob(companyDetail.getCompany_whatJob());
        companyDetailUpdateReq.setCompanyDetail_id(companyDetail.getCompanyDetail_id());

        model.addAttribute("companyDetail", companyDetailUpdateReq);
        model.addAttribute("companyName", companyDetailUpdateReq.getCompanyDetail_Cname());
        return mav;
    }

    @Autowired
    private CompanyListMapper companyListMapper;

    @Autowired
    private CompanyDetailMapper companyDetailMapper;

    @Autowired
    private CompanyListService companyListService;

    @Autowired
    private CompanyDetailService companyDetailService;

    CompanyList companyList;

    CompanyDetail companyDetail;
}
