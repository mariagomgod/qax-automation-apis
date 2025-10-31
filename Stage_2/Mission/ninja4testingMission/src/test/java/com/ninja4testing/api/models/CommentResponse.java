package com.ninja4testing.api.models;

public class CommentResponse {
    private Integer id;
    private Integer post_id;
    private String name;
    private String email;
    private String body;

    public CommentResponse(Integer id, Integer post_id, String name, String email, String body) {
        this.id = id;
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public CommentResponse() {}

    public Integer getId() { return id; }
    public Integer getPost_id() { return post_id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBody() { return body; }

    public void setId(Integer id) { this.id = id;}
    public void setPost_id(Integer post_id) { this.post_id = post_id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBody(String body) { this.body = body; }
}
