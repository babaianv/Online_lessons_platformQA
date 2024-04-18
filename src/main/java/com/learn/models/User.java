package com.learn.models;

public class User {
    private  String nickname;
    private  String email;
    private  String password;
    private String error;

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }


    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getError() {
        return error;
    }

    public User setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
