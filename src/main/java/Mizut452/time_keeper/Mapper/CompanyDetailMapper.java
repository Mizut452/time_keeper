package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyDetail;
import Mizut452.time_keeper.Model.Entity.CompanyList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyDetailMapper {
    @Select("SELECT id, companyName FROM companyList where companyName = #{companyName}")
    List<CompanyList> selectIDandName(@Param("companyName") String companyName);

    @Select("SELECT * FROM companyDetail")
    List<CompanyDetail> selectAll();

    @Select("SELECT * FROM companyDetail where companyDetail_Cname = #{companyName}")
    List<CompanyDetail> selectACompany(@Param("companyName") String companyName);

    @Insert("INSERT INTO companyDetail(company_whatJob, company_strongPoint, company_weakPoint, company_treatment, company_welfare, company_flow, company_another) VALUE(" +
            "#{company_whatJob}, #{company_strongPoint}, #{company_weakPoint}, #{company_treatment}, #{company_welfare}, #{company_flow}, #{company_another})")
    void add(CompanyDetail companyDetail);

    @Insert("INSERT INTO companyDetail(companyDetail_id, companyDetail_Cname) SELECT id, companyName FROM companyList WHERE companyName = #{companyName}")
    void addCname(CompanyDetail companyDetail);

    @Select("SELECT id FROM companyList where companyName = #{companyName}")
    int selectIdByCompanyName(String companyName);

    @Insert("INSERT INTO companyDetail(companyDetail_id, companyDetail_Cname) SELECT id, companyName FROM companyList WHERE companyName = #{companyName}")
    void addIdAndName(@Param("companyName") String companyName);

    @Select("SELECT * FROM companyDetail WHERE companyName = #{companyName}")
    CompanyDetail findById(@Param("companyName") String companyName);

}
