package com.yashdev.journalApp.services;

import com.yashdev.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.yashdev.journalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    // This class is an implementation of the UserDetailsService interface, which is a core interface in Spring Security. It is used to retrieve user-related data. The UserDetailsService interface has a single method, loadUserByUsername, which is used to look up a user by their username and return a UserDetails object that Spring Security can use for authentication and authorization.

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);
        if(user != null){
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))// Convert List<String> to String[] also it has automatic resizing if roles > 0
                    .build();
            return userDetails;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
