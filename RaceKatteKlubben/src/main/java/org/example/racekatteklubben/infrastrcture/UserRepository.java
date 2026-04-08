package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public UserLogin logUserIn(UserLogin userLogin) {
        return null;
    }
}
