package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> handleGetAllUsers(){
        return userRepository.requestFullUserList();
    }
}
