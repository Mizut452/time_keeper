package Mizut452.time_keeper.Model.Entity;

import java.util.List;

public class Record {
    public record UserRecord(String mailaddress, String username, String password, List<String> roleList) {}
}