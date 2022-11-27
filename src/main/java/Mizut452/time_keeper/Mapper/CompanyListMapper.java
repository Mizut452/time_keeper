package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.CompanyList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyListMapper {
    @Select("SELECT * FROM companyList")
    List<CompanyList> selectAll();

    @Insert("INSERT INTO companyList(companyName, industry, headlocate, areOsaka, CompanyURL, companyLother) VALUES(#{companyName}, #{industry}, #{headlocate}, #{areOsaka}, #{CompanyURL}, #{companyLother})")
    void add(CompanyList companyList);
}
