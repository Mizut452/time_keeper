package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Model.Entity.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService {
    private final LoginUserRepository loginUserRepository;

    @Transactional(readOnly = true)
    public List<LoginUser> selectAll() {
        return loginUserRepository.selectAll();
    }
}
