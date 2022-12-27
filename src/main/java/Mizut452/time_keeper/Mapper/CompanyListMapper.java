package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyList;
import Mizut452.time_keeper.Model.Entity.CompanyListUpdateReq;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyListMapper {
    @Select("SELECT * FROM companyList")
    List<CompanyList> selectAll();

    @Insert("INSERT INTO companyList(companyName, industry, headLocate, areOsaka, CompanyURL, companyListOther) VALUES(#{companyName}, #{industry}, #{headLocate}, #{areOsaka}, #{CompanyURL}, #{companyListOther})")
    void add(CompanyList companyList);

    @Select("SELECT companyName FROM companyList WHERE companyName = #{companyName}")
    List<CompanyList> selectCompanyName(String companyName);

    @Select("SELECT * FROM companyList WHERE companyName = #{companyName}")
    CompanyList findByCompanyName(@Param("companyName") String companyName);

    @Update("UPDATE companyList SET companyName = #{companyName}, industry = #{industry}, headLocate = #{headLocate}, areOsaka = #{areOsaka}, companyURL = #{companyURL}, companyListOther = #{companyListOther} where id = #{id}")
    void update(CompanyListUpdateReq companyListUpdateReq);

    @Delete("DELETE FROM companyList WHERE companyName = #{companyName}")
    void deleteCompanyList(String cn);
}
