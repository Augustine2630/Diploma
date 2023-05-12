package com.example.aug.tokenservice.controller;

import com.example.aug.tokenservice.util.TokenManager;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/token")
public class TokenAutherController {

   private final TokenManager tokenManager;

    public TokenAutherController(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @GetMapping("/token")
    public String token(@RequestParam("username") String username){
        return tokenManager.generateJwtToken(username);
    }

    @GetMapping("valid")
    public Boolean valid(@RequestHeader("Authorization") String token, @RequestParam("username") String username){
        return tokenManager.validateJwtToken(token, username);
    }

}
