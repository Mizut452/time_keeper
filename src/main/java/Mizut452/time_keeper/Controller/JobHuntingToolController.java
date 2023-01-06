package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.CompanyDetailMapper;
import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.*;
import Mizut452.time_keeper.Service.CompanyDetailService;
import Mizut452.time_keeper.Service.CompanyListService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobHuntingToolController {
    @RequestMapping("/company_add")
    public String addCompanyItem(@AuthenticationPrincipal LoginUser loginUser,
                                 CompanyList companyList,
                                 CompanyDetail companyDetail,
                                 String companyName,
                                 Model model) {
        companyList.setCompanyName(getCompanyName);
        companyList.setIndustry(getIndustry);
        companyList.setHeadLocate(getHeadLocate);
        companyList.setCompanyURL(getCompanyURL);
        companyList.setCompanyListOther(getCompanyListOther);
        companyDetail.setCompanyDetail_CompanyName(getCompanyName);
        companyDetail.setCompanyDetail_id(getCompanyListId);

        companyListService.addCompanyList(companyList);
        companyDetailService.addCompanyDetail(companyDetail);

        companyName = companyList.getCompanyName();
        model.addAttribute("CompanyList", companyListMapper_selectAll);
        String PrincipalUserName = loginUser.getUsername();
        model.addAttribute("TimeList", PrincipalUserName);
        model.addAttribute("CompanyDetail", companyDetailMapper_selectAll);


        return "JobHuntingTool";
    }

    @GetMapping("/jobHuntingTool")
    public ModelAndView JobHuntPage(@AuthenticationPrincipal LoginUser loginUser) {
        ModelAndView mav = new ModelAndView("JobHuntingTool");
        mav.addObject("CompanyList", companyListMapper_selectAll);
        String PrincipalUserName = loginUser.getUsername();
        mav.addObject("TimeList", PrincipalUserName);
        mav.addObject("CompanyDetail", companyDetailMapper_selectAll);
        return mav;
    }

    @GetMapping("/jobHuntingTool/{companyName}")
    public Object JobCompanyPage(@ModelAttribute CompanyList companyList,
                                 @PathVariable String companyName,
                                 @AuthenticationPrincipal LoginUser loginUser,
                                 CompanyDetail companyDetail,
                                 ModelAndView mav) {
        String PrincipalUserName = loginUser.getUsername();
        CompanyList record = companyListMapper.findByCompanyName(companyName);

        if(record == null) {
            mav.addObject("TimeList", PrincipalUserName);
            return "NullCompany";
        } else {
            mav = new ModelAndView("JobHuntingCompany");
            companyDetail = companyDetailMapper.findById(companyName);
            CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
            companyDetailUpdateReq.setCompanyDetail_id(getCompanyDetail_id);
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
                                  ModelAndView mav,
                                  Model model) {
        mav = new ModelAndView("companyEditPage");
        CompanyList companyList = companyListService.findByCompanyName(companyName);
        CompanyListUpdateReq companyListUpdateReq = new CompanyListUpdateReq();
        companyListUpdateReq.setId(getCompanyListId);
        companyListUpdateReq.setCompanyName(getCompanyName);
        companyListUpdateReq.setHeadlocate(getHeadLocate);
        companyListUpdateReq.setIndustry(getIndustry);
        companyListUpdateReq.setCompanyURL(getCompanyURL);
        companyListUpdateReq.setCompanyLother(getCompanyListOther);
        companyListUpdateReq.setAreOsaka(getAreOsaka);
        model.addAttribute("companyList", companyListUpdateReq);

        CompanyDetail companyDetail = companyDetailService.findByCompanyName(companyName);
        CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
        companyDetailUpdateReq.setCompany_weakPoint(getCompany_weakPoint);
        companyDetailUpdateReq.setCompany_strongPoint(getCompany_strongPoint);
        companyDetailUpdateReq.setCompany_another(getCompany_another);
        companyDetailUpdateReq.setCompany_flow(getCompany_flow);
        companyDetailUpdateReq.setCompany_treatment(getCompany_treatment);
        companyDetailUpdateReq.setCompany_welfare(getCompany_welfare);
        companyDetailUpdateReq.setCompany_whatJob(getCompany_whatJob);
        companyDetailUpdateReq.setCompanyDetail_Cname(getCompanyDetail_CompanyName);
        companyDetailUpdateReq.setCompanyDetail_id(getCompanyDetail_id);
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
    public Object deleteCompanyConfirm(@ModelAttribute CompanyDetailUpdateReq companyDetailUpdateReq,
                                       @PathVariable("id") int id,
                                       Model model,
                                       ModelAndView mav) {
        mav = new ModelAndView("Confirm");
        CompanyDetail companyDetail = companyDetailService.findById(id);
        companyDetailUpdateReq = new CompanyDetailUpdateReq();
        companyDetailUpdateReq.setCompany_another(getCompany_another);
        companyDetailUpdateReq.setCompany_treatment(getCompany_treatment);
        companyDetailUpdateReq.setCompany_welfare(getCompany_welfare);
        companyDetailUpdateReq.setCompany_flow(getCompany_flow);
        companyDetailUpdateReq.setCompanyDetail_Cname(getCompanyDetail_CompanyName);
        companyDetailUpdateReq.setCompany_strongPoint(getCompany_strongPoint);
        companyDetailUpdateReq.setCompany_weakPoint(getCompany_weakPoint);
        companyDetailUpdateReq.setCompany_whatJob(getCompany_whatJob);
        companyDetailUpdateReq.setCompanyDetail_id(getCompanyDetail_id);
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

    //companyListのgetter
    int getCompanyListId = companyList.getId();
    String  getCompanyName = companyList.getCompanyName();
    String getIndustry = companyList.getIndustry();
    String getHeadLocate = companyList.getHeadLocate();
    boolean getAreOsaka = companyList.isAreOsaka();
    String getCompanyURL = companyList.getCompanyURL();
    String getCompanyListOther = companyList.getCompanyListOther();

    //companyDetailのgetter
    int getCompanyDetail_id = companyDetail.getCompanyDetail_id();
    String getCompanyDetail_CompanyName =companyDetail.getCompanyDetail_CompanyName();
    String getCompany_whatJob = companyDetail.getCompany_whatJob();
    String getCompany_strongPoint = companyDetail.getCompany_strongPoint();
    String getCompany_weakPoint = companyDetail.getCompany_weakPoint();
    String getCompany_treatment = companyDetail.getCompany_treatment();
    String getCompany_welfare = companyDetail.getCompany_welfare();
    String getCompany_flow = companyDetail.getCompany_flow();
    String getCompany_another = companyDetail.getCompany_another();

    //Mapper
    List<CompanyList> companyListMapper_selectAll = companyListMapper.selectAll();
    List<CompanyDetail> companyDetailMapper_selectAll = companyDetailMapper.selectAll();


}
