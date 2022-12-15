package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyDetailUpdateReq;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface CompanyDetailMapper {

    @Select("SELECT * FROM companyDetail")
    List<CompanyDetail> selectAll();

    @Select("SELECT * FROM companyDetail where companyDetail_Cname = #{companyName}")
    List<CompanyDetail> selectACompany(@Param("companyName") String companyName);

    @Insert("INSERT INTO companyDetail(companyDetail_Cname, company_whatJob, company_strongPoint, company_weakPoint, company_treatment, company_welfare, company_flow, company_another) VALUES(#{companyDetail_Cname}, '', '', '', '', '', '', '')")
    void add(CompanyDetail companyDetail);

    @Insert("INSERT INTO companyDetail(companyDetail_Cname) SELECT companyName FROM companyList WHERE companyName = #{companyName}")
    void addCname(CompanyDetail companyDetail);

    @Insert("INSERT INTO companyDetail(companyDetail_id, companyDetail_Cname) SELECT id, companyName FROM companyList WHERE companyName = #{companyName}")
    void addIdAndName(@Param("companyName") String companyName);

    @Select("SELECT * FROM companyDetail WHERE companyDetail_Cname = #{companyName}")
    CompanyDetail findById(@Param("companyName") String companyName);

    @Update("UPDATE companyDetail SET company_whatJob = #{company_whatJob}, company_strongPoint = #{company_strongPoint}, company_weakPoint = #{company_weakPoint}, company_treatment = #{company_treatment}, company_welfare = #{company_welfare}, company_flow = #{company_flow}, company_another = #{company_another} WHERE companyDetail_id = #{companyDetail_id}")
    void update(CompanyDetailUpdateReq companyDetailUpdateReq);

    @Delete("DELETE FROM companyDetail WHERE companyDetail_id = #{companyDetail_id}")
    void deleteCompanyDetail(int id);

}
