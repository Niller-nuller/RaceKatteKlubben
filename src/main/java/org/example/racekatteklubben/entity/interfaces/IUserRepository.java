package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.AuthObject;
import org.example.racekatteklubben.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IUserRepository {
    void createUserCredentials(AuthObject userAuth);
    AuthObject logUserIn(AuthObject userAuth);
    void createUser(User user, AuthObject userAuth);
    AuthObject getUserLoginId(AuthObject userAuth);
    List<User> requestUserListPopulate();
    User getUserFromAuthObject(AuthObject userAuth);
    void updateUser(User user);
    void requestDeleteUser(User user);
}
