package com.diploma.authorization.controller;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.User;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.JwtRequestModel;
import com.diploma.authorization.util.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final UserDetailsServiceImpl userDetailsService;

    public RegistrationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public void performRegistration(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpServletRequest request){
        System.out.println(request.toString());
        System.out.println(userDTO);
        userDetailsService.saveNewUser(convertToUser(userDTO));
    }

    private User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setBirthDate(userDTO.getBirthDate());
        user.setDepartment(null);
        return user;
    }

}
