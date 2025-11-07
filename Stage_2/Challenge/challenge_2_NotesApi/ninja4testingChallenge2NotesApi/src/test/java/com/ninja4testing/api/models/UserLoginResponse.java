package com.ninja4testing.api.models;

public class UserLoginResponse {

    private boolean success;
    private Integer status;
    private String message;
    private LoginData data;

    public UserLoginResponse(boolean success, Integer status, String message, LoginData data) {
        this.success = success;
        this.status = status;
        this.message= message;
        this.data = data;
    }

    public UserLoginResponse() {}

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }
    public void setData(LoginData data) {
        this.data = data;
    }
}
