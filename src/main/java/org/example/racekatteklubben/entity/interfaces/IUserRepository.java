package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Auth;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    String getEmail(Auth userLogin);
    void createUserCredentials(Auth userLogin);
    Auth logUserIn(Auth userLogin);
}
