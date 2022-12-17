package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyDetailUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
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
            //会社名でSQL内を検索、そのカラムの１つを取り出し
            List<CompanyDetail> companyDetails = companyDetailMapper.selectACompany("SHARP");
            assertEquals("日本屈指の大企業", companyDetails.get(0).getCompany_strongPoint());

    }

    @Test
        void doDelete() {
            List<CompanyDetail> companyDetails = companyDetailMapper.selectACompany("SHARP");
            //まだdeleteしていないので、この時点では会社名をゲッターしても存在する。
            assertEquals("SHARP", companyDetails.get(0).getCompanyDetail_Cname());
            companyDetailMapper.deleteCompanyDetail("SHARP");
            //Deleteが成功していれば、リストが消えるのでエラーが発生する
            List<CompanyDetail> deleteDetails = companyDetailMapper.selectACompany("SHARP");
            assertEquals(null, deleteDetails.get(0).getCompanyDetail_Cname());
    }
    @Test
        void doUpdate() {
        //update前の確認
            String beforeUpdate = companyDetailMapper.selectACompany("SHARP").get(0).getCompany_strongPoint();
            assertEquals("日本屈指の大企業", beforeUpdate);

        //updateする
            CompanyDetailUpdateReq companyDetailUpdateReq = new CompanyDetailUpdateReq();
            companyDetailUpdateReq.setCompanyDetail_Cname("SHARP");
            companyDetailUpdateReq.setCompanyDetail_id(1);
            companyDetailUpdateReq.setCompany_whatJob("営業コース");
            companyDetailUpdateReq.setCompany_strongPoint("世界的な企業");
            companyDetailUpdateReq.setCompany_weakPoint("難しい");
            companyDetailUpdateReq.setCompany_welfare("育休あり");
            companyDetailUpdateReq.setCompany_treatment("年俸制");
            companyDetailUpdateReq.setCompany_flow("最終面接");
            companyDetailUpdateReq.setCompany_another("");
            companyDetailMapper.update(companyDetailUpdateReq);
        //updateがきちんとされているか確認
            assertEquals("世界的な企業", companyDetailMapper.selectACompany("SHARP").get(0).getCompany_strongPoint());
    }


}
