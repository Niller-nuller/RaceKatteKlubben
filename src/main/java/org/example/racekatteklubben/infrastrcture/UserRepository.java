package org.example.racekatteklubben.infrastrcture;


import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;


    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Auth logUserIn(Auth userLogin) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Auth(
                        rs.getString("email"),
                        rs.getString("password")
                ),
                userLogin.getEmail()
        );
    }
    @Override
    public String getEmail(Auth userLogin){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> rs.getString("email"),
                    userLogin.getEmail());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    @Override
    public void createUserCredentials(Auth userLogin) {
        String sql = "INSERT INTO Credentials (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userLogin.getEmail(), userLogin.getPassword());
    }
}
