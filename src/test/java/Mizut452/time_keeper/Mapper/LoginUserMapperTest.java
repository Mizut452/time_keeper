package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.LoginUserTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginUserMapperTest {
    @Select("SELECT * FROM testList")
    List<LoginUserTest> selectAll();

    @Insert("INSERT INTO testList (mailaddress, username, password, rolename) VALUES (#{mailaddress}, #{username}, #{@password}, 'ROLE_GENERAL')")
    void create(LoginUserTest loginUserTest);
}
