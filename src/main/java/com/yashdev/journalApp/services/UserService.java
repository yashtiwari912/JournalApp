package com.yashdev.journalApp.services;


//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database


import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //we did not implement it as spring implements it itself

    public void saveEntry(User user) {
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
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

}
