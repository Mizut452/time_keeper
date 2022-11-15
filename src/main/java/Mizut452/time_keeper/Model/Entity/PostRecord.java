package Mizut452.time_keeper.Model.Entity;

public class PostRecord {
    private int id;
    private String mailaddress;
    private String username;
    private String password;
    private String roleName;

    public PostRecord() {
    }

    public PostRecord(int id, String mailaddress, String username, String password, String roleName) {
        this.id = id;
        this.mailaddress = mailaddress;
        this.username =username;
        this.password = password;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
