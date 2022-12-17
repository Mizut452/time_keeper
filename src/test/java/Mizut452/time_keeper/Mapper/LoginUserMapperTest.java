package Mizut452.time_keeper.Mapper;

import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.LoginUserTest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LoginUserMapperTest {
    @Autowired
    LoginUserMapper loginUserMapper;

    @Test
    void findAllTest() {
        List<LoginUser> loginUsers = loginUserMapper.selectAll();
        assertEquals(3, loginUsers.size());
    }

    @Test
    void createAccountTest() {
        LoginUser loginUser = new LoginUser();
        loginUser.setMailaddress("testMan@example.com");
        loginUser.setUsername("testMan");
        loginUser.setPassword("testMan");
        loginUserMapper.create(loginUser);
        List<LoginUser> loginUsers = loginUserMapper.selectAll();
        assertEquals(4, loginUsers.size());
        assertEquals("ROLE_GENERAL",loginUser.getRoleName());
    }

    @Test
    void findByUsername() {
        LoginUser loginUser = loginUserMapper.findByUsername("top");
        String testRole = loginUser.getRoleName();
        //このテストは失敗します
        assertEquals("ROLE_GENERAL", testRole);
        //このテストは成功します。
        assertEquals("ROLE_ADMIN", testRole);
    }
}
