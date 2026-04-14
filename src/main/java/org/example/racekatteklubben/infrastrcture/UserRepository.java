package org.example.racekatteklubben.infrastrcture;


import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Auth logUserIn(Auth auth) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Auth(
                        rs.getString("email"),
                        rs.getString("password")
                ),
                auth.getEmail()
        );
    }
    @Override
    public String getEmail(Auth auth){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> rs.getString("email"),
                auth.getEmail());
    }
    @Override
    public void createUserCredentials(Auth auth) {
        String sql = "INSERT INTO Credentials (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, auth.getEmail(), auth.getPassword());
    }
}
