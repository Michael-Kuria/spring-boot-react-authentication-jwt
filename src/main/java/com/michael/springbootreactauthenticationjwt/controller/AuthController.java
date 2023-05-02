package com.michael.springbootreactauthenticationjwt.controller;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import com.michael.springbootreactauthenticationjwt.model.AuthRequest;
import com.michael.springbootreactauthenticationjwt.model.AuthResponse;
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
    private String tokenB;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, ApplicationUserService applicationUserService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.applicationUserService = applicationUserService;
    }

    @GetMapping("/home")
    public String home(Principal principal){
        return principal.toString();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/login")
    public AuthResponse token(@RequestBody AuthRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            ApplicationUser user = (ApplicationUser) applicationUserService.loadUserByUsername(authentication.getName());

            System.out.println("What is happening");
            LOG.info("Token requested for user: '{}'",user.toString());
            tokenB = tokenService.generateToken(user);
            LOG.info("Token granted: {}", tokenB);
            return new AuthResponse(tokenB);

        }catch (BadCredentialsException ex){
            throw new BadCredentialsException(ex.getMessage());
//            System.out.println( ex.getMessage());
        }

    }

//
//    @CrossOrigin(origins = "http://localhost:8080")
//    @GetMapping("/login")
//    public AuthResponse getAuthentication(){
//
//        return new AuthResponse(tokenB);
//    }


}
