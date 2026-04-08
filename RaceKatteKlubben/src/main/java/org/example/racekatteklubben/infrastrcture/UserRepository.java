package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.UserLogin;
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
    public String getEmail(UserLogin userLogin){
        String sql = "select * from users where email = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("email")).get(0);
    }
    @Override
    public void createUser(UserLogin userLogin) {
        String sql = "insert into users (email, password) values (?, ?)";
        jdbcTemplate.update(sql, userLogin.getEmail(), userLogin.getPassword());

    }
}
