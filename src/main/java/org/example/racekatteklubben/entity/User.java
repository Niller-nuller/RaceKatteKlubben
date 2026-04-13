package org.example.racekatteklubben.entity;

public class User {
    private long id;
    private String name;
    private int age;
    private Gender gender;
    private Auth usersLogin;

    public User(){}
    public User(String name, int age, Gender gender, Auth usersLogin) {
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
    public Auth getUsersLogin() {
        return usersLogin;
    }
    public void setUsersLogin(Auth usersLogin) {
        this.usersLogin = usersLogin;
    }
}
