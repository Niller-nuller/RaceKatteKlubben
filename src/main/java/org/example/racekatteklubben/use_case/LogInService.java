package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    private final HttpSession session;
    private final IUserRepository userRepository;
    @Autowired
    public LogInService(IUserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public User login(Auth loginRequest) {
        Auth authLogin = userRepository.logUserIn(loginRequest);
        if (authLogin != null && BCrypt.checkpw(loginRequest.getPassword(), authLogin.getPassword())) {
            User user = userRepository.getUserFromAuth(loginRequest);
            return user;
        }
        return null;
    }
}
