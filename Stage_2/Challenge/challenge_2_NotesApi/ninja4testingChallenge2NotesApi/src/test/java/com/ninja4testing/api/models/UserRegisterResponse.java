package com.ninja4testing.api.models;

public class UserRegisterResponse {
    private boolean success;
    private Integer status;
    private String message;
    private BaseUserData data;

    public UserRegisterResponse(boolean success, Integer status, String message, BaseUserData data) {
        this.success = success;
        this.status = status;
        this.message= message;
        this.data = data;
    }

    public UserRegisterResponse() {}

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

    public BaseUserData getData() {
        return data;
    }
    public void setData(BaseUserData data) {
        this.data = data;
    }
}
