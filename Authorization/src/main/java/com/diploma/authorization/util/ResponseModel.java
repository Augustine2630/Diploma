package com.diploma.authorization.util;

import com.diploma.authorization.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class ResponseModel {

    private String token;

    private UserDetailsImpl userDetails;

    public ResponseModel(String token, UserDetailsImpl userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }
}
