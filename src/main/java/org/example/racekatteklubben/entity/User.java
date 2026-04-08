package org.example.racekatteklubben.entity;

public class User {
    private long id;
    private String name;
    private int age;
    private Gender gender;
    private UserLogin usersLogin;

    public User(String name, int age, Gender gender, UserLogin usersLogin) {
        this.name = name;
        this.age = age;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
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
