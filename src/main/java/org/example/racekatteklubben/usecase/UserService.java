package org.example.racekatteklubben.usecase;

import org.example.racekatteklubben.entity.AuthObject;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private final ValidationService validationService;
    private final IUserRepository userRepository;
    private final List<User> fullServerUserList;
    private final List<User> fullFilteredUserList;

    @Autowired
    public UserService(ValidationService validationService ,IUserRepository userRepository,List<User> fullServerUserList,List<User> fullFilteredUserList) {
        this.validationService = validationService;
        this.userRepository = userRepository;
        this.fullServerUserList = fullServerUserList;
        this.fullFilteredUserList = fullFilteredUserList;
    }

    public List<User> handleReturnFullUserList(String criteria){
        if(fullServerUserList.isEmpty()){
            fullServerUserList.addAll(userRepository.requestUserListPopulate());
        }
        if(criteria.isBlank()){
            return fullServerUserList;
        }
        if(!fullFilteredUserList.isEmpty()){
            fullFilteredUserList.clear();
        }
        for(User user : fullServerUserList){
            if(user.getName().contains(criteria)){
                fullFilteredUserList.add(user);
            }
        }
        return fullServerUserList;
    }

    public User getUserFromAuth(AuthObject auth) {
        return userRepository.getUserFromAuthObject(auth);
    }

    public void updateUser(User formUser, AuthObject auth) {
        User existingUser = userRepository.getUserFromAuthObject(auth);
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
