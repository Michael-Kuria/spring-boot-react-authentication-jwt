package com.michael.springbootreactauthenticationjwt.service;

import com.michael.springbootreactauthenticationjwt.model.ApplicationUser;
import com.michael.springbootreactauthenticationjwt.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository userRepository;

    public ApplicationUserService(ApplicationUserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
