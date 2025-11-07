package com.ninja4testing.api.utils;

import com.ninja4testing.api.models.UserLoginRequest;
import com.ninja4testing.api.models.UserLoginResponse;
import com.ninja4testing.api.models.UserRegisterRequest;
import com.ninja4testing.api.models.UserRegisterResponse;
import io.restassured.response.Response;

public final class RunContext {
    private static String userName;
    private static String email;
    private static String password;
    private static String loginId;
    private static String token;
    private static Response response;
    private static UserRegisterRequest requestBody;
    private static UserRegisterResponse responseBody;
    private static UserLoginRequest requestLoginBody;
    private static UserLoginResponse responseLoginBody;

    private RunContext() {}

    public static String getUserName() { return userName; }
    public static String getEmail() { return email; }
    public static String getPassword() { return password; }
    public static String getLoginId()   { return loginId; }
    public static String getToken()    { return token; }
    public static Response getResponse() { return response; }
    public static UserRegisterRequest getRequestBody() { return requestBody; }
    public static UserRegisterResponse getResponseBody() { return responseBody; }
    public static UserLoginRequest getRequestLoginBody() { return requestLoginBody; }
    public static UserLoginResponse getResponseLoginBody() { return responseLoginBody; }

    public static void setUserName(String userName) { RunContext.userName = userName; }
    public static void setEmail(String email) { RunContext.email = email; }
    public static void setPassword(String password) { RunContext.password = password; }
    public static void setLoginId(String userId)         { RunContext.loginId = loginId; }
    public static void setToken(String token)           { RunContext.token = token;  }
    public static void setResponse(Response response) { RunContext.response = response; }
    public static void setRequestBody(UserRegisterRequest requestBody) { RunContext.requestBody = requestBody; }
    public static void setResponseBody(UserRegisterResponse responseBody) { RunContext.responseBody = responseBody; }
    public static void setRequestLoginBody(UserLoginRequest requestLoginBody) { RunContext.requestLoginBody = requestLoginBody; }
    public static void setResponseLoginBody(UserLoginResponse responseLoginBody) { RunContext.responseLoginBody = responseLoginBody; }
}
