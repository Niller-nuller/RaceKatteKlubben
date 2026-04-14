package org.example.racekatteklubben.entity;

public class RegisterWrapper {
    private User user;
    private UserLogin userLogin;

    public RegisterWrapper(){}
    public RegisterWrapper(User user, UserLogin userLogin) {
        this.user = user;
        this.userLogin = userLogin;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserLogin getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}

