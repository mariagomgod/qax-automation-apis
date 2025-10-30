package com.ninja4testing.api.utils;

import com.ninja4testing.api.models.CommentsRequest;
import com.ninja4testing.api.models.CommentsResponse;
import io.restassured.response.Response;

import java.util.List;

public class RunContext {

    private static String endpoint;
    private static Response response;
    private static CommentsRequest requestBody;
    private static CommentsResponse responseBody;
    private static int postId;
    private static List<CommentsResponse> listResponseBody;

    // Constructor vacío
    public RunContext() {}

    // Getters y setters
    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        RunContext.endpoint = endpoint;
    }

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        RunContext.response = response;
    }

    public static CommentsRequest getRequestBody() {
        return requestBody;
    }

    public static void setRequestBody(CommentsRequest requestBody) {
        RunContext.requestBody = requestBody;
    }

    public static CommentsResponse getResponseBody() {
        return responseBody;
    }

    public static void setResponseBody(CommentsResponse responseBody) {
        RunContext.responseBody = responseBody;
    }

    public static int getPostId() {
        return postId;
    }

    public static void setPostId(int postId) {
        RunContext.postId = postId;
    }

    public static List<CommentsResponse> getListResponseBody() {
        return listResponseBody;
    }

    public static void setListResponseBody(List<CommentsResponse> listResponseBody) {
        RunContext.listResponseBody = listResponseBody;
    }
}
