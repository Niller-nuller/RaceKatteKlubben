package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LogInService {


    private final IUserRepository userRepository;
    @Autowired
    public LogInService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserLogin login(UserLogin loginRequest) {
        UserLogin userLogin = userRepository.logUserIn(loginRequest);
        if (userLogin != null && BCrypt.checkpw(loginRequest.getPassword(), userLogin.getPassword())) {
            return userLogin;
        }
        return null;
    }
}
