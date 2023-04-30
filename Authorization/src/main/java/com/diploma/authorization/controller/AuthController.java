package com.diploma.authorization.controller;

import com.diploma.authorization.model.Addresses;
import com.diploma.authorization.repository.UserRepository;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.JwtRequestModel;
import com.diploma.authorization.util.ResponseModel;
import com.diploma.authorization.util.TokenManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    private final TokenManager tokenManager;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserDetailsServiceImpl userDetailsService, UserRepository userRepository, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel> createToken(@RequestBody JwtRequestModel
                                                request) throws Exception {
        System.out.println(request.getUsername() + request.getPassword());
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
//        String jwtToken = null;
//        UserDetails userDetails = null;
        return ResponseEntity.ok(new ResponseModel(jwtToken, userDetails));
    }

    @RequestMapping("/logout")
    public void logout(){

    }

    @GetMapping("/update-address")
    public void updateUserAddress(@RequestParam("id") Integer id, @RequestParam("address") String address){
        userRepository.setUserAddress(address, id);
    }

    @GetMapping("/address-list")
    public List<Addresses> getAllByUser(@RequestParam("user") String user){
        return userDetailsService.getByUser(user);
    }

    @PostMapping("/new-address")
    public void addNewAddress(@RequestParam("address") String address, @RequestParam("user") String user){
        userDetailsService.addNewAddress(address, user);
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('USER')")
    public String admin(){
        return "admin";
    }
}
