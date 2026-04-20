package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IUserRepository {
    void createUserCredentials(Auth userAuth);
    Auth logUserIn(Auth userAuth);
    void createUser(User user, Auth userAuth);
    Auth getUserLoginId(Auth userAuth);
    List<User> requestUserListPopulate();
    User getUserFromAuth(Auth userAuth);
    void updateUser(User user);
    void requestDeleteUser(User user);
}
