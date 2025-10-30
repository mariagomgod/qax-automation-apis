package com.ninja4testing.api.models;

public class CommentsResponse {

    public int postId;
    public String name;
    public String email;
    public String body;
    public Integer id;

    public int getPostId() { return postId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBody() { return body; }
    public Integer getId() { return id; }
}
