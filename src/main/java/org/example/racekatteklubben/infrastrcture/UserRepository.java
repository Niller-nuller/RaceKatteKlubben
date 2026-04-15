package org.example.racekatteklubben.infrastrcture;


import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Auth logUserIn(Auth userAuth) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Auth(
                        rs.getLong("CredentialsId"),
                        rs.getString("Email"),
                        rs.getString("Password")
                ),
                userAuth.getEmail()
        );
    }

    @Override
    public User getUserFromAuth(Auth userAuth){
        String sql = "SELECT * FROM Users LEFT JOIN Credentials ON Users.CredentialsId = Credentials.CredentialsId WHERE Users.CredentialsId = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new User(
                        rs.getLong("UserId"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        Gender.valueOf(rs.getString("Gender")),
                        new Auth(
                                userAuth.getEmail(), ""
                        )
                ), userAuth.getId()
        );
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE Users SET Name = ?, LastName = ?, Gender = ? WHERE UserId = ?";
        jdbcTemplate.update(sql, user.getName(), user.getLastName(), user.getGender().name(), user.getId());
    }

    @Override
    public String getEmail(Auth userAuth){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        // try er kun til hvis databasen er tom
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> rs.getString("email"),
                    userAuth.getEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void createUserCredentials(Auth userAuth) {
        String sql = "INSERT INTO Credentials (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userAuth.getEmail(), userAuth.getPassword());
    }

    @Override
    public void createUser(User user, Auth userAuth) {
        String sql = "INSERT INTO Users (Name, LastName, Gender, CredentialsID) VALUES (?, ?, ?, ?)";

        userAuth.setId(getUserLoginId(userAuth).getId());
        jdbcTemplate.update(sql, user.getName(), user.getLastName(), user.getGender().toString(), userAuth.getId());
    }

    @Override
    public Auth getUserLoginId(Auth userAuth){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Auth(rs.getLong("CredentialsId"), rs.getString("Email"), rs.getString("Password")), userAuth.getEmail()
        );
    }
    @Override
    public List<User> requestFullUserList(String sql, String safeCriteria){
        return jdbcTemplate.query(sql,(rs, rowNum) ->
                new User(rs.getLong("UserId"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        Gender.valueOf(rs.getString("Gender")),
                        new Auth(rs.getString("Email"), rs.getString("Password"))),safeCriteria);
    }
    @Override
    public List<User> requestFullFilteredUserList(String criteria){
        String sql = "SELECT * FROM Users LEFT JOIN Credentials on Users.credentialsId = Credentials.CredentialsId WHERE name Like ?";
        String safeCriteria = "%" + criteria + "%";
        return requestFullUserList(sql, safeCriteria);
    }

    @Override
    public void requestDeleteUser(User user){
        String deleteCatsSql = "DELETE FROM Cats WHERE OwnerId = ?";
        String deleteUserSql = "DELETE FROM Users WHERE UserId = ?";
        String deleteCredentialsSql = "DELETE FROM Credentials WHERE CredentialsId = ?";

        jdbcTemplate.update(deleteCatsSql, user.getId());
        jdbcTemplate.update(deleteUserSql, user.getId());
        jdbcTemplate.update(deleteCredentialsSql, user.getId());
    }
}
