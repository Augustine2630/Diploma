package com.aug.adminservice.util;

import org.springframework.security.core.userdetails.UserDetails;

public class UserResponseModel {

    private String jwtToken;
    private UserDetails user;

    public UserResponseModel(String jwtToken, UserDetails user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
