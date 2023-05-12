package com.diploma.authorization.controller;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.User;
import com.diploma.authorization.repository.RoleRepository;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegistrationController {

    private final UserDetailsServiceImpl userDetailsService;
    private final RoleRepository roleRepository;

    public RegistrationController(UserDetailsServiceImpl userDetailsService, RoleRepository roleRepository) {
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public void performRegistration(@RequestBody Map<String, Object> payload){
        UserDTO userDTO = new UserDTO();

        payload.forEach((k, v) -> {
            v = org.springframework.util.StringUtils.trimAllWhitespace(v.toString());
            userDTO.setUsername(StringUtils.substringBetween(v.toString(), "username=", ",password="));
            userDTO.setPassword(new BCryptPasswordEncoder().encode(StringUtils.substringBetween(v.toString(), "password=", ",birthDate")));;
            userDTO.setFirstName(StringUtils.substringBetween(v.toString(), "firstName=", ",lastName="));
            userDTO.setLastName(StringUtils.substringBetween(v.toString(), "lastName=", "}"));
        });
        userDetailsService.saveNewUser(convertToUser(userDTO));
        try {
            roleRepository.addNewRoleWithUser("USER",  Math.toIntExact(userDetailsService.getUserId(userDTO.getUsername())));
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.OK);
        }
    }


    private User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPatronymic(null);
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setBirthDate(null);
        user.setDepartment(null);
        return user;
    }

}
