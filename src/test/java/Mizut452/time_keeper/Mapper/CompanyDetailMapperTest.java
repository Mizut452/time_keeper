package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyDetailUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyDetailMapperTest {
    @Autowired
            CompanyDetailMapper companyDetailMapper;

    @Test
        void doSelectAll() {
            List<CompanyDetail> companyDetails = companyDetailMapper.selectAll();
            assertEquals(1, companyDetails.size());
      }

    @Test
        void doSelectACompany() {
            List<CompanyDetail> companyDetails = companyDetailMapper.selectACompany("SHARP");
            assertEquals("日本屈指の大企業", companyDetails.get(0).getCompany_strongPoint());

    }

    @Test
        void doAdd() {
            companyDetailMapper.deleteCompanyDetail("SHARP");
            assertEquals(null, companyDetailMapper.selectAll().get(0).getCompanyDetail_Cname());
    }


}
