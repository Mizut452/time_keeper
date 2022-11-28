package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginUserMapper {
    @Select("SELECT * FROM userlist")
    List<LoginUser> selectAll();
    @Select("SELECT username FROM userlist WHERE username = #{username}")
    LoginUser selectUsername(String username);

    @Select("SELECT * FROM userlist WHERE username = #{username}")
    LoginUser findByUsername(@Param("username") String username);

    //@Select("SELECT * FROM userlist where username = #{username}")
    //public LoginUser findByUsername(String username);

    public void create(LoginUser loginUser);
}
