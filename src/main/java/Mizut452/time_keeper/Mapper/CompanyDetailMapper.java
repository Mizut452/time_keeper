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

    @Insert("INSERT INTO companyDetail(company_whatJob, company_strongPoint, company_weakPoint, company_treatment, company_welfare, company_flow, company_another) VALUE(" +
            "#{company_whatJob}, #{company_strongPoint}, #{company_weakPoint}, #{company_treatment}, #{company_welfare}, #{company_flow}, #{company_another})")
    @Options(useGeneratedKeys = true, keyProperty = "companyD_id")
    void add(CompanyDetail companyDetail);
}
