package org.example.racekatteklubben.infrastrcture;


import org.example.racekatteklubben.entity.AuthObject;
import org.example.racekatteklubben.entity.Gender;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
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
    public AuthObject logUserIn(AuthObject userAuth) {
        String sql = "SELECT * FROM Credentials WHERE email = ?";

        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new AuthObject(
                        rs.getLong("CredentialsId"),
                        rs.getString("Email"),
                        rs.getString("Password")
                ),
                userAuth.getEmail()
        );
    }

    @Override
    public User getUserFromAuthObject(AuthObject userAuth){
        String sql = "SELECT * FROM Users LEFT JOIN Credentials ON Users.CredentialsId = Credentials.CredentialsId WHERE Users.CredentialsId = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new User(
                        rs.getLong("UserId"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        Gender.valueOf(rs.getString("Gender")),
                        new AuthObject(
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
    public void createUserCredentials(AuthObject userAuth) {
        String sql = "INSERT INTO Credentials (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, userAuth.getEmail(), userAuth.getPassword());
    }

    @Override
    public void createUser(User user, AuthObject userAuth) {
        String sql = "INSERT INTO Users (Name, LastName, Gender, CredentialsID) VALUES (?, ?, ?, ?)";

        userAuth.setId(getUserLoginId(userAuth).getId());
        jdbcTemplate.update(sql, user.getName(), user.getLastName(), user.getGender().toString(), userAuth.getId());
    }

    @Override
    public AuthObject getUserLoginId(AuthObject userAuth){
        String sql = "SELECT * FROM Credentials WHERE email = ?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new AuthObject(rs.getLong("CredentialsId"), rs.getString("Email"), rs.getString("Password")), userAuth.getEmail()
        );
    }

    @Override
    public List<User> requestUserListPopulate(){
        String sql = "SELECT * FROM users LEFT JOIN credentials on users.CredentialsId = credentials.CredentialsId ORDER BY users.Name ASC";
        return jdbcTemplate.query(sql,(rs, rowNum) ->
                new User(rs.getLong("UserId"),
                        rs.getString("Name"),
                        rs.getString("LastName"),
                        Gender.valueOf(rs.getString("Gender")),
                        new AuthObject(rs.getString("Email"), rs.getString("Password"))));
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
