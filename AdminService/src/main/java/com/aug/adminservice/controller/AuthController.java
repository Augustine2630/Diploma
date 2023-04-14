package com.aug.adminservice.controller;

import com.aug.adminservice.service.UserDetailsServiceImpl;
import com.aug.adminservice.util.JwtRequestModel;
import com.aug.adminservice.util.ResponseModel;
import com.aug.adminservice.util.TokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    private final TokenManager tokenManager;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserDetailsServiceImpl userDetailsService, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> createToken(@RequestBody JwtRequestModel
                                                request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new
                            UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new ResponseModel(jwtToken, userDetails));
    }

    @RequestMapping("/logout")
    public void logout(){

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('USER')")
    public String admin(){
        return "admin";
    }
}
