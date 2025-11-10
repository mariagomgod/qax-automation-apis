package com.ninja4testing.api.utils;

import io.restassured.response.Response;

public final class RunContext {

    private static Integer id;
    private static Response response;
    private static Response responseBody;

    private RunContext() {}

    public static Integer getId()   { return id; }
    public static Response getResponse() { return response; }
    public static Response getResponseBody() { return responseBody; }

    public static void setId(Integer id)         { RunContext.id = id; }
    public static void setResponse(Response response) { RunContext.response = response; }
    public static void setResponseBody(Response responseBody) { RunContext.responseBody = responseBody; }
}
