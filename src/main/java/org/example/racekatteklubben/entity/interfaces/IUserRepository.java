package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.UserLogin;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    String getEmail(UserLogin userLogin);
    void createUserCredentials(UserLogin userLogin);
    UserLogin logUserIn(UserLogin userLogin);
}
