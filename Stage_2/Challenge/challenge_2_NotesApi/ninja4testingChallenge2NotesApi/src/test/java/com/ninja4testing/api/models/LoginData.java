package com.ninja4testing.api.models;

public class LoginData extends BaseUserData {

    private String token;

    public LoginData() {
        // super() se encarga de llamar al constructor de la clase padre (BaseUserData en este caso)
        super();
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
