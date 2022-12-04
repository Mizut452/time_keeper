package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.CompanyListMapper;
import Mizut452.time_keeper.Model.Entity.CompanyList;
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
}
