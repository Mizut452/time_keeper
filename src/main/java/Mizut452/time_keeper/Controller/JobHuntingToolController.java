package Mizut452.time_keeper.Controller;

import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import Mizut452.time_keeper.Service.CompanyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JobHuntingToolController {

    @RequestMapping("/company_add")
    public String addCompanyItem(@ModelAttribute CompanyList companyList) {
        companyList.setCompanyName(companyList.getCompanyName());
        companyList.setIndustry(companyList.getIndustry());
        companyList.setHeadlocate(companyList.getHeadlocate());
        companyList.setCompanyURL(companyList.getCompanyURL());
        companyList.setCompanyLother(companyList.getCompanyLother());
        companyListService.addCompanyList(companyList);
        return "redirect:/jobHuntingTool";
    }

    @GetMapping("/jobHuntingTool")
    public ModelAndView JobHuntPage() {
        ModelAndView mav = new ModelAndView("JobHuntingTool");
        mav.addObject("CompanyList", companyListMapper.selectAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String PrincipalUserName = principal.getUsername();
        mav.addObject("TimeList", PrincipalUserName);
        return mav;
    }

    @GetMapping("/jobHuntingTool/{companyName}")
    public Object JobCompanyPage(@ModelAttribute CompanyList companyList,
                                       @PathVariable String companyName,
                                 ModelAndView mav) {
        CompanyList record = companyListMapper.findByCompanyName(companyName);

        if(record == null) {
            return "NullCompany";
        } else {
            mav = new ModelAndView("JobHuntingCompany");

            mav.addObject("CompanyName", companyListMapper.selectCompanyName(companyName));
            return mav;
        }
    }

    @Autowired
    private CompanyListMapper companyListMapper;

    @Autowired
    private CompanyListService companyListService;

}
