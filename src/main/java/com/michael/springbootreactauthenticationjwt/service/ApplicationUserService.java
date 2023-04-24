package com.michael.springbootreactauthenticationjwt.service;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import com.michael.springbootreactauthenticationjwt.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository userRepository;
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationUserService.class);
    public ApplicationUserService(ApplicationUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOG.info("loaduserbyusername has been called for authentication");
        Optional<ApplicationUser> user = userRepository.findById(email);

        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException(String.format("User with email %s was not found", email));
    }


    public void addApplicationUser(ApplicationUser user){
        userRepository.save(user);
    }


    public long applicationUserCount(){
        return userRepository.count();
    }
}
