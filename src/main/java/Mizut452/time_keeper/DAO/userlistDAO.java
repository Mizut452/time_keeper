package Mizut452.time_keeper.DAO;

/*import java.util.Collections;
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
                        row.get("roleName").toString()))
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
                        row.get("roleName").toString()))
                .toList();

        return adminLists;
    }
}*/
