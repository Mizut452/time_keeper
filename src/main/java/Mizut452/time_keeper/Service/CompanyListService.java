package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import Mizut452.time_keeper.Model.Entity.CompanyListUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyListService {
    @Autowired
    CompanyListMapper companyListMapper;

    public void addCompanyList(CompanyList companyList) {
        companyListMapper.add(companyList);
    }

    public void update(CompanyListUpdateReq companyListUpdateReq) {
        companyListMapper.update(companyListUpdateReq);
    }

    public CompanyList findByCompanyName(String companyName) {
        return companyListMapper.findByCompanyName(companyName);
    }

    public void deleteCompanyList(String cn) {
        companyListMapper.deleteCompanyList(cn);
    }
}
