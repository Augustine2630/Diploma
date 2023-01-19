package com.diploma.authorization.controller;

import com.diploma.authorization.model.User;
import com.diploma.authorization.security.UserDetailsImpl;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.ResponseModel;
import com.diploma.authorization.util.TokenManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    private final TokenManager tokenManager;


    public AuthController(UserDetailsServiceImpl userDetailsService, TokenManager tokenManager) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> loginUser(@RequestBody User user) {

        final UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
                .loadUserByUsername(user.getUsername());


        final String jwtToken = tokenManager.generateJwtToken(userDetails);

        return ResponseEntity.ok(HttpStatus.OK).ok(new ResponseModel(jwtToken, userDetails));
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
