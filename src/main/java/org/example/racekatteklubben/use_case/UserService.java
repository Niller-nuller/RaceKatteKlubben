package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ValidationService validationService;
    private final IUserRepository userRepository;
    private final List<User> fullServerUserList;

    @Autowired
    public UserService(ValidationService validationService ,IUserRepository userRepository,List<User> fullServerUserList) {
        this.validationService = validationService;
        this.userRepository = userRepository;
        this.fullServerUserList = fullServerUserList;
    }

    public List<User> handleReturnFullUserList(String criteria){
        if(fullServerUserList.isEmpty()){
            fullServerUserList.addAll(userRepository.requestUserListPopulate());
        }
        if(criteria.isEmpty()){
            return fullServerUserList;
        }
        return fullServerUserList.stream()
                .filter(cat -> cat.getName()
                        .toLowerCase()
                        .contains(criteria.toLowerCase()))
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }

    public User getUserFromAuth(Auth auth) {
        return userRepository.getUserFromAuth(auth);
    }

    public void updateUser(User formUser, Auth auth) {
        User existingUser = userRepository.getUserFromAuth(auth);
        User updatedUser = new User(
                existingUser.getId(),
                formUser.getName(),
                formUser.getLastName(),
                formUser.getGender(),
                auth
        );
        userRepository.updateUser(updatedUser);
    }
    public void handleDeleteUser(User user) {
        //Validate lol
        userRepository.requestDeleteUser(user);
    }
}
