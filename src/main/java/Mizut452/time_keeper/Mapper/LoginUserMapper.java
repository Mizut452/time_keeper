package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginUserMapper {
    @Select("SELECT * FROM userList")
    List<LoginUser> selectAll();
    @Select("SELECT username FROM userList WHERE username = #{username}")
    LoginUser selectUsername(String username);

    @Select("SELECT * FROM userList u JOIN users_role ur ON u.id = ur.user_id JOIN roles r ON ur.role_id = r.roleID WHERE username = #{username}")
    LoginUser findByUsername(@Param("username") String username);


    void create(LoginUser loginUser);
}
