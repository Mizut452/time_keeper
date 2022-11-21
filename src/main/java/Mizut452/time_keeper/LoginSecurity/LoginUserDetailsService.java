package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Model.Entity.Record.UserRecord;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService {
    private final LoginUserRepository repo;

    public LoginUserDetailsService(LoginUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserRecord> userOp = repo.findByUsername(username);
        return userOp.map(userRecord -> new LoginUserDetails(userRecord))
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}
