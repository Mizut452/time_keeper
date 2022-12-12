package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.LoginUserMapperTest;
import Mizut452.time_keeper.Model.Entity.LoginUserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateAccountServiceTest {
    @Autowired
    LoginUserMapperTest loginUserMapperTest;

    @Autowired
    PasswordEncoder encoder;

    public void createAccountTest(LoginUserTest loginUserTest) {
        loginUserTest.setPassword(encoder.encode(loginUserTest.getPassword()));
        loginUserMapperTest.create(loginUserTest);
    }


}
