package com.yashdev.journalApp.services;


//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database


import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //we did not implement it as spring implements it itself

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Implementing Logger
    //SL4J is a logging Abstraction Framework with the help of which we can talk to underlining implementation
    //Now instead of doing this Logger logger we will use @Slf4j annotation provided by lombok which will automatically generate a logger for us and we can use it directly with the name "log"
    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);


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
            //{} is a placeholder for the variable that we want to log, in this case, user.getUserName() will be logged in place of {}.
            //This allows us to log dynamic information without having to concatenate strings manually.
            log.error("Error Occured for user {}" , user.getUserName(), e);
//            logger.warn("hahhaaa");
//            logger.info("hahhaaa");
//            logger.debug("hahhaaa");
//            logger.trace("hahhaaa");
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
