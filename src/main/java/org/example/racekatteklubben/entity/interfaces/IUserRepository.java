package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IUserRepository {
    String getEmail(Auth userAuth);
    void createUserCredentials(Auth userAuth);
    Auth logUserIn(Auth loginRequest);
    User getUserByCredentialsId(long id);
    void createUser(User user, Auth userAuth);
    Auth getUserLoginId(Auth userAuth);
    List<User> requestFullUserList();
    User getUserFromAuth(Auth userAuth);
}
