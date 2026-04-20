package org.example.racekatteklubben.entity;

public class RegisterWrapper {
    private User user;
    private AuthObject authLogin;

    public RegisterWrapper(){}
    public RegisterWrapper(User user, AuthObject authLogin) {
        this.user = user;
        this.authLogin = authLogin;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public AuthObject getAuthLogin() {
        return authLogin;
    }
    public void setAuthLogin(AuthObject userAuth) {
        this.authLogin = userAuth;
    }
}

