package Mizut452.time_keeper.DAO;

import Mizut452.time_keeper.Model.Record;
import Mizut452.time_keeper.Model.Record.AdminList;
import Mizut452.time_keeper.Model.Record.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class userlistDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    userlistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(UserList userlists) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(userlists);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("userlist");
        insert.execute(param);
    }

    public List<UserList> findAll() {
        String query = "SELECT * FROM userlist";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<UserList> userlists = result.stream()
                .map((Map<String, Object> row) -> new UserList(
                        //(int)row.get("id"),
                        row.get("mailaddress").toString(),
                        row.get("username").toString(),
                        row.get("password").toString(),
                        row.get("rolename").toString()))
                .toList();

        return userlists;
    }
    public List<AdminList> AdminFindAll() {
        String query = "SELECT * FROM userlist";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);
        List<AdminList> adminLists = result.stream()
                .map((Map<String, Object> row) -> new AdminList(
                        (int)row.get("id"),
                        row.get("mailaddress").toString(),
                        row.get("username").toString(),
                        row.get("password").toString(),
                        row.get("rolename").toString()))
                .toList();

        return adminLists;
    }
}
