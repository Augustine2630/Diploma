package com.diploma.authorization.controller;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.User;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import com.diploma.authorization.util.JwtRequestModel;
import com.diploma.authorization.util.ResponseModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final UserDetailsServiceImpl userDetailsService;

    public RegistrationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public void performRegistration(@RequestBody Map<String, Object> payload){
        UserDTO userDTO = new UserDTO();
        payload.forEach((k, v) -> {
            v = org.springframework.util.StringUtils.trimAllWhitespace(v.toString());
            userDTO.setUsername(StringUtils.substringBetween(v.toString(), "username=", ",password="));
            userDTO.setPassword(StringUtils.substringBetween(v.toString(), "password=", ",birthDate"));
            userDTO.setBirthDate(StringUtils.substringBetween(v.toString(), "birthDate=", ",firstName"));
            userDTO.setFirstName(StringUtils.substringBetween(v.toString(), "firstName=", ",lastName="));
            userDTO.setLastName(StringUtils.substringBetween(v.toString(), "lastName=", "}"));
        });
        userDetailsService.saveNewUser(convertToUser(userDTO));
    }


    private User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
//        user.setBirthDate(userDTO.getBirthDate());
        user.setDepartment(null);
        return user;
    }

}
