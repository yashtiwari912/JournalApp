package com.yashdev.journalApp.services;


//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database


import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //we did not implement it as spring implements it itself

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user) {
        userRepository.save(user);
    }
    public boolean saveNewUser(User user) {
        // Hash the password before saving
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            user.setRoles(Arrays.asList("USER"));// Set default role as USER
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void saveAdminUser(User user){
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRoles(Arrays.asList("USER","ADMIN"));// Set default role as USER
        userRepository.save(user);
    }
    public List<User>getAll(){
        return userRepository.findAll();
    }
    public Optional<User> getUserByID(ObjectId id){
        return userRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
