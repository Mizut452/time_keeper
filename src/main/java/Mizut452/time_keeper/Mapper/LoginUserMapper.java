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

    @Select("SELECT * FROM userList WHERE username = #{username}")
    LoginUser findByUsername(@Param("username") String username);

    @Insert("INSERT INTO userList (mailAddress, username, password, roleName) VALUES(#{mailAddress}, #{username}, #{password}, 'ROLE_GENERAL')")
    void create(LoginUser loginUser);

    //@Insert("INSERT INTO users_role(user_id, role_id) SELECT id FROM userList WHERE username = #{username}, VALUES(2)")
    //void createUsersRole(LoginUser loginUser);
}
