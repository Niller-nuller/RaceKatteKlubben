package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.UserLogin;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository {

    UserLogin logUserIn(UserLogin userLogin);
}
