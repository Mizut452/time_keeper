package Mizut452.time_keeper.LoginSecurity;


import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoginUserRepository {
    private final LoginUserMapper loginUserMapper;

    public List<LoginUser> selectAll() {
        return loginUserMapper.selectAll();
    }

    public LoginUser get(String username) {
        return loginUserMapper.get(username);
    }
}
