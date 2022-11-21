package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Model.Entity.Record.UserRecord;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUserDetails implements UserDetails {
    private final UserRecord userRecord;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(UserRecord userRecord) {
        this.userRecord = userRecord;
        this.authorities = userRecord.roleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .toList();
    }

    public UserRecord getUserRecord() {
        return userRecord;
    }


    @Override
    public String getPassword() {
        return userRecord.password();
    }

    @Override
    public String getUsername() {
        return userRecord.username();
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
