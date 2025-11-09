package com.ninja4testing.api.utils;

import com.ninja4testing.api.models.CommentRequest;
import com.ninja4testing.api.models.CommentResponse;
import com.ninja4testing.api.models.PostRequest;
import com.ninja4testing.api.models.PostResponse;
import com.ninja4testing.api.models.UserRequest;
import com.ninja4testing.api.models.UserResponse;
import io.restassured.response.Response;

public final class RunContext {
    private static Integer userId;
    private static Integer postId;
    private static Integer commentId;
    private static String token;
    private static Response response;
    private static UserRequest userRequestBody;
    private static UserResponse userResponseBody;
    private static PostRequest postRequestBody;
    private static PostResponse postResponseBody;
    private static CommentRequest commentRequestBody;
    private static CommentResponse commentResponseBody;

    private RunContext() {}

    public static Integer getUserId()   { return userId; }
    public static Integer getPostId()   { return postId; }
    public static Integer getCommentId()   { return commentId; }
    public static String getToken()    { return token; }
    public static Response getResponse() { return response; }
    public static UserRequest getUserRequestBody() { return userRequestBody; }
    public static PostRequest getPostRequestBody() { return postRequestBody; }
    public static CommentRequest getCommentRequestBody() { return commentRequestBody; }
    public static CommentResponse getCommentResponseBody() { return commentResponseBody; }

    public static void setUserId(Integer userId)         { RunContext.userId = userId; }
    public static void setPostId(Integer postId)         { RunContext.postId = postId; }
    public static void setCommentId(Integer commentId)         { RunContext.commentId = commentId; }
    public static void setToken(String token)           { RunContext.token  = token;  }
    public static void setResponse(Response response) { RunContext.response = response; }
    public static void setUserRequestBody(UserRequest userRequestBody) { RunContext.userRequestBody = userRequestBody; }
    public static void setUserResponseBody(UserResponse userResponseBody) { RunContext.userResponseBody = userResponseBody; }
    public static void setPostRequestBody(PostRequest postRequestBody) { RunContext.postRequestBody = postRequestBody; }
    public static void setPostResponseBody(PostResponse postResponseBody) { RunContext.postResponseBody = postResponseBody; }
    public static void setCommentRequestBody(CommentRequest commentRequestBody) { RunContext.commentRequestBody = commentRequestBody; }
    public static void setCommentResponseBody(CommentResponse commentResponseBody) { RunContext.commentResponseBody = commentResponseBody; }

}
