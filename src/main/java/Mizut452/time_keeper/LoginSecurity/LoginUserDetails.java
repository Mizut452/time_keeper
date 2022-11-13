package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Model.Record.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {
    private LoginUser loginUser;
    private final Collection<GrantedAuthority> authorities;

    public LoginUserDetails(LoginUser loginUser,
                            Collection<GrantedAuthority> authorities) {
        this.loginUser = loginUser;
        this.authorities = authorities;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }


    @Override
    public String getPassword() {
        return loginUser.password();
    }

    @Override
    public String getUsername() {
        return loginUser.username();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
