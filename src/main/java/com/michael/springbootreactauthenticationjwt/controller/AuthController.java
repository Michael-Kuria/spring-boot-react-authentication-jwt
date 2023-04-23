package com.michael.springbootreactauthenticationjwt.controller;

import com.michael.springbootreactauthenticationjwt.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/home")
    public String home(Principal principal){
        return principal.toString();
    }

    @PostMapping("/token")
    public String token(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return token;
    }


}
