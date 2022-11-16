package Mizut452.time_keeper.Model.Entity;

import lombok.Data;

@Data
public class LoginUser {
    private int id;
    private String mailaddress;
    private String username;
    private String password;
    private String roleName;
}
