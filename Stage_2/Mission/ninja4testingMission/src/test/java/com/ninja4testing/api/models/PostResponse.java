package com.ninja4testing.api.models;

public class PostResponse {
    private Integer id;
    private Integer user_id;
    private String title;
    private String body;

    public PostResponse(Integer id, Integer user_id, String title, String body) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }

    public PostResponse() {}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() { return body; }
    public void setBody(String body) {
        this.body = body;
    }
}
