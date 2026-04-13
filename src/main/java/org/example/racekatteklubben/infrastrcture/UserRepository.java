package org.example.racekatteklubben.infrastrcture;


import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.UserLogin;
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
    public UserLogin logUserIn(UserLogin userLogin) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new UserLogin(
                        rs.getString("email"),
                        rs.getString("password")
                ),
                userLogin.getEmail()
        );
    }
    @Override
    public String getEmail(UserLogin userLogin){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        // try er kun til hvis databasen er tom
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> rs.getString("email"),
                    userLogin.getEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void createUserCredentials(UserLogin userLogin) {
        String sql = "INSERT INTO Credentials (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userLogin.getEmail(), userLogin.getPassword());
    }

    @Override
    public void createUser(User user, UserLogin userLogin) {
        String sql = "INSERT INTO Users (Name, LastName, Gender, CredentialsID) VALUES (?, ?, ?, ?)";

        userLogin.setId(getUserLoginId(userLogin).getId());
        jdbcTemplate.update(sql, user.getName(), user.getLastName(), user.getGender().toString(), userLogin.getId());
    }

    @Override
    public UserLogin getUserLoginId(UserLogin userLogin){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new UserLogin(rs.getLong("CredentialsId"), rs.getString("Email"), rs.getString("Password")), userLogin.getEmail()
        );
    }

}
