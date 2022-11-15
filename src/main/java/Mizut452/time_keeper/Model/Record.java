package Mizut452.time_keeper.Model;

import java.util.List;

public class Record {
    public record AdminList(int id, String mailaddress, String username, String password, String roleName) {}
    public record UserList(String mailaddress, String username, String password, String roleName){}
    public record LoginUser(String mailaddress, String username, String password, List<String> roleList) {}

}
