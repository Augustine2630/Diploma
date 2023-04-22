package com.diploma.authorization.util;

import com.diploma.authorization.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String JWTtoken;

    private UserDetails userDetails;


    public ResponseModel(String JWTtoken, UserDetails userDetails) {
        this.JWTtoken = JWTtoken;
        this.userDetails = userDetails;
    }

    public String getJWTtoken() {
        return JWTtoken;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
