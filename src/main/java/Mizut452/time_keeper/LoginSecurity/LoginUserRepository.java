package Mizut452.time_keeper.LoginSecurity;

import Mizut452.time_keeper.Model.Record.LoginUser;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginUserRepository {
    private final String SQL_FIND_BY_USERNAME = """
            SELECT
            mailaddress,
            username,
            password,
            rolename
            FROM userlist
            WHERE username = :username
            """;


    private static final ResultSetExtractor<LoginUser> LOGIN_USER_RESULT_SET_EXTRACTOR = (rs) -> {
        String mailaddress = null;
        String username = null;
        String password = null;
        List<String> roleList = new ArrayList<>();

        while (rs.next()) {
            if (username == null) {
                mailaddress = rs.getString("mailaddress");
                username = rs.getString("username");
                password = rs.getString("password");
            }
            roleList.add(rs.getString("rolename"));

            if (username == null) {
                return null;
            }
        }
        return new LoginUser(mailaddress, username, password, roleList);
    };

        private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        }
        public Optional<LoginUser> findByUsername(String username){
            MapSqlParameterSource params = new MapSqlParameterSource("username", username);

            LoginUser loginUser = namedParameterJdbcTemplate.query(SQL_FIND_BY_USERNAME, params, LOGIN_USER_RESULT_SET_EXTRACTOR);
            return Optional.ofNullable(loginUser);
        }

    }