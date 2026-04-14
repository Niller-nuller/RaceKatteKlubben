package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Auth;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {
    String getEmail(Auth auth);
    void createUserCredentials(Auth auth);
    Auth logUserIn(Auth auth);
}
