/*package Mizut452.time_keeper;

import Mizut452.time_keeper.Mapper.LoginUserMapperTest;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.LoginUserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TimeKeeperApplicationTests {
	@Autowired
	LoginUserMapperTest loginUserMapperTest;

	@Test
	void contextLoads() {
		List<LoginUser> loginUserTests = loginUserMapperTest.selectAll();
		assertEquals(2, loginUserTests.size());
	}

	@Test
	void createAccount() {
		LoginUser loginUser = new LoginUser();
		loginUser.setMailaddress("testMan@example.com");
		loginUser.setUsername("testMan");
		loginUser.setPassword("testMan");
		loginUserMapper.create(loginUser);
		List<LoginUser> loginUserTests = loginUserMapperTest.selectAll();
		assertEquals(3, loginUserTests.size());
	}


}*/
