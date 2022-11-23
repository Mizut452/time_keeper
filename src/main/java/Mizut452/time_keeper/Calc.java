package Mizut452.time_keeper;

import Mizut452.time_keeper.Mapper.LoginUserMapper;
import Mizut452.time_keeper.Model.Entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Calc {
    public static void main(String[] args) {

        LoginUserMapper loginUserMapper;

        String username ="";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) auth.getPrincipal();
        String principalUsername = principal.getUsername();

    }
}