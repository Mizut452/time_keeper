package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.CompanyDetailMapper;
import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyDetailService {
    @Autowired
    CompanyDetailMapper companyDetailMapper;

    @Autowired
    CompanyListMapper companyListMapper;

    public void addCompanyDetail(CompanyDetail companyDetail) {
        companyDetailMapper.add(companyDetail);
    }

    public void addCompanyName(CompanyDetail companyDetail) {
        companyDetailMapper.addCname(companyDetail);
    }

    public void addIdandNameService(String companyName) {
        companyDetailMapper.addIdAndName(companyName);
    }
}
