package com.ninja4testing.api.models;

public class CommentsRequest {

    private int postId;
    private String name;
    private String email;
    private String body;

    public CommentsRequest() {}

    public CommentsRequest(int postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }
    public int getPostId() { return postId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBody() { return body; }

    public void setPostId(int postId) { this.postId = postId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBody(String body) { this.body = body; }
}
