package com.michael.springbootreactauthenticationjwt.controller;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import com.michael.springbootreactauthenticationjwt.model.AuthRequest;
import com.michael.springbootreactauthenticationjwt.service.ApplicationUserService;
import com.michael.springbootreactauthenticationjwt.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ApplicationUserService applicationUserService;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, ApplicationUserService applicationUserService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/home")
    public String home(Principal principal){
        return principal.toString();
    }

    @PostMapping("/login")
    public String token(@RequestBody AuthRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            ApplicationUser user = (ApplicationUser) applicationUserService.loadUserByUsername(request.getEmail());

            System.out.println("What is happening");
            LOG.info("Token requested for user: '{}'",user.toString());
            String token = tokenService.generateToken((ApplicationUser) user);
            LOG.info("Token granted: {}", token);
            return token;

        }catch (BadCredentialsException ex){
            return ex.getMessage();
        }


    }

    @GetMapping("/authenticated")
      public String getAuthentication(Authentication authentication){
        return authentication.getName();
    }


}
