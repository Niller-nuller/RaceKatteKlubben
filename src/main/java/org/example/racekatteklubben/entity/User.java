package org.example.racekatteklubben.entity;

public class User {
    private long id;
    private String name;
    private String lastName;
    private Gender gender;
    private UserLogin usersLogin;

    public User(){}
    public User(String name,String lastName, Gender gender, UserLogin usersLogin) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersLogin = usersLogin;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
    }
    public String getName() {
        return name;
    }
    public  void setName(String name) {
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public UserLogin getUsersLogin() {
        return usersLogin;
    }
    public void setUsersLogin(UserLogin usersLogin) {
        this.usersLogin = usersLogin;
    }
}
