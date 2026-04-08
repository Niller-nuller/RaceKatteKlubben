package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserLogin logUserIn(UserLogin userLogin) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.query(sql,
                rs -> {
                    if (rs.next()) {
                        String storedHash = rs.getString("password");
                        if (encoder.matches(userLogin.getPassword(), storedHash)) {
                            UserLogin result = new UserLogin(rs.getString("email"), storedHash);
                            result.setId(rs.getLong("id"));
                            return result;
                        }
                    }
                    return null;
                },
                userLogin.getEmail()
        );
    }
}
