package com.ninja4testing.api.models.user;

import java.util.List;

public class UserResponse {
    private String userID;
    private String username;
    private List<Object> books;

    public UserResponse() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Object> getBooks() {
        return books;
    }

    public void setBooks(List<Object> books) {
        this.books = books;
    }
}
