package Mizut452.time_keeper.Model.Entity;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class LoginUser {
    private int id;
    private String mailaddress;
    private String username;
    private String password;
    private String roleName;
    private List<String> roleList;


}
