package com.diploma.authorization.controller;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.Role;
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
    public void performRegistration(@RequestBody Map<String, Object> payload) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(StringUtils.substringBetween(payload.toString(), "username=", ", password="));
        userDTO.setPassword(new BCryptPasswordEncoder().encode(StringUtils.substringBetween(payload.toString(), "password=", ", firstName=")));
        userDTO.setFirstName(StringUtils.substringBetween(payload.toString(), "firstName=", ", lastName="));
        userDTO.setLastName(StringUtils.substringBetween(payload.toString(), "lastName=", "}"));
        userDetailsService.saveNewUser(convertToUser(userDTO));
        Role role = new Role();
        role.setRole("USER");
        role.setUser(convertToUser(userDTO));
        roleRepository.save(role);
        ResponseEntity.status(HttpStatus.OK);
    }


    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setDepartment(null);
        return user;
    }

}
