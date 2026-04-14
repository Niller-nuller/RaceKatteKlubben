package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.UserLogin;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IUserRepository {
    String getEmail(UserLogin userLogin);
    void createUserCredentials(UserLogin userLogin);
    UserLogin logUserIn(UserLogin userLogin);
    void createUser(User user, UserLogin userLogin);
    UserLogin getUserLoginId(UserLogin userLogin);
    List<User> requestFullUserList();
}
