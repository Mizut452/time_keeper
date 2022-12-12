package Mizut452.time_keeper;

import Mizut452.time_keeper.Mapper.LoginUserMapperTest;
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
		List<LoginUserTest> loginUserTests = loginUserMapperTest.selectAll();
		assertEquals(2, loginUserTests.size());
	}

}
