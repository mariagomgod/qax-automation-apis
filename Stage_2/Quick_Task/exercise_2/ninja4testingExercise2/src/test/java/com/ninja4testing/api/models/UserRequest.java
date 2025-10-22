package com.ninja4testing.api.models;


public class UserRequest {
    private String userName;
    private String password;

    public UserRequest() {}

    public UserRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserRequest(String title, String body, Integer userId) {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
