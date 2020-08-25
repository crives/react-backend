package com.cognixia.jump.service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userFound = userRepository.findByEmail(userName);
        if(!userFound.isPresent()) {
            throw new UsernameNotFoundException("Email " + userName + " does not exists");
        }
        return new MyUserDetails(userFound.get());
    }
}
