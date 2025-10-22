package com.ninja4testing.api.models;

public class PostRequest {

    private String title;
    private String body;
    private Integer userId;

    public PostRequest() {}
    public PostRequest(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public Integer getUserId() { return userId; }

    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
