package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    private IUserRepository userRepository;

    @Autowired
    public LogInService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
