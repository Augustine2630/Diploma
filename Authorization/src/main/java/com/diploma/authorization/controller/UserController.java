package com.diploma.authorization.controller;

import com.diploma.authorization.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/update-address")
    public void updateUserAddress(@RequestParam("id") Integer id, @RequestParam("address") String address){
        userRepository.setUserAddress(address, id);
    }
}
