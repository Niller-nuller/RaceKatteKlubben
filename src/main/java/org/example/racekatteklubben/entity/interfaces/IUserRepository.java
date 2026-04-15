package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IUserRepository {
    String getEmail(Auth userAuth);
    void createUserCredentials(Auth userAuth);
    Auth logUserIn(Auth userAuth);
    void createUser(User user, Auth userAuth);
    Auth getUserLoginId(Auth userAuth);
    List<User> requestFullUserList(String sql, String safeCriteria);
    List<User> requestFullFilteredUserList(String criteria);
    User getUserFromAuth(Auth userAuth);
    void updateUser(User user);
    void requestDeleteUser(User user);
}
