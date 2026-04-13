package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogOutService {

    private final IUserRepository userRepository;
    @Autowired
    public LogOutService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
