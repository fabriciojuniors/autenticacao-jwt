package com.jwt.models.dtos;

public class LoginDTO {

    private String username;
    private String password;
    private Boolean googleAccount;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isGoogleAccount() {
        return googleAccount;
    }
}
