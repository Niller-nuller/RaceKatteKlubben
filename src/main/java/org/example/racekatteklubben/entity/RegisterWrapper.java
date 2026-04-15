package org.example.racekatteklubben.entity;

public class RegisterWrapper {
    private User user;
    private Auth authLogin;

    public RegisterWrapper(){}
    public RegisterWrapper(User user, Auth authLogin) {
        this.user = user;
        this.authLogin = authLogin;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Auth getAuthLogin() {
        return authLogin;
    }
    public void setAuthLogin(Auth userAuth) {
        this.authLogin = userAuth;
    }
}

