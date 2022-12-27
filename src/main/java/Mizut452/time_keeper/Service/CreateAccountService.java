package Mizut452.time_keeper.Service;

import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateAccountService {
    @Autowired
    LoginUserMapper loginUserMapper;

    @Autowired
    PasswordEncoder encoder;

    public void createAccount(LoginUser loginUser) {
        loginUser.setPassword(encoder.encode(loginUser.getPassword()));
        loginUserMapper.create(loginUser);
        //loginUserMapper.createUsersRole(loginUser);
    }
}
