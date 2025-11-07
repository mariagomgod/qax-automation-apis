package com.ninja4testing.api.models;

public class BaseUserData {

    // He creado este POJO aparte con los campos comunes que van a devolver las responses de los 3 scenarios.
    // Lo he creado para reutilizar información y no crear estos mismos POJOs dentro de los cada POJO de cada response.
    // El código quedaría más limpio y entendible.
    private String id;
    private String name;
    private String email;

    public BaseUserData() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
