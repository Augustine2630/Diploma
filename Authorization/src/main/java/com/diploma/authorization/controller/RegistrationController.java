package com.diploma.authorization.controller;

import com.diploma.authorization.DTO.UserDTO;
import com.diploma.authorization.model.Role;
import com.diploma.authorization.model.User;
import com.diploma.authorization.repository.RoleRepository;
import com.diploma.authorization.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> performRegistration(@RequestBody Map<String, UserDTO> user){
//        System.out.println(userDTO);
//        userDetailsService.saveNewUser(convertToUser(userDTO));
        System.out.println(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setBirthDate(userDTO.getBirthDate());
        user.setDepartment(null);
        Role role = new Role();
        role.setRole("USER");
        role.setUser(user);
        roleRepository.save(role);
        return user;
    }

}
