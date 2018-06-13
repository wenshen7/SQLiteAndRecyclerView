package com.example.fireb.userlisting;

import java.util.Date;

public class User {
    String username, name, email, joinDate, DOB;
    char gender;

    public  User(){

    }

    public User(String username, String name, String email, char gender, String joinDate, String DOB) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.joinDate = joinDate;
        this.DOB = DOB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", joinDate=" + joinDate +
                ", DOB=" + DOB +
                '}';
    }
}
