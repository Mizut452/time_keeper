package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import Mizut452.time_keeper.Model.Entity.Record.UserRecord;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    private final LoginUserMapper loginUserMapper;

    public LoginUserDetailsService(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser loginUser = loginUserMapper.selectUsername(username);
        return loginUser;
    }
}
