package com.yashdev.journalApp.controller;


import com.yashdev.journalApp.entity.JournalEntry;
import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.services.JournalEntryService;
import com.yashdev.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database

//ResponseEntity<?> means that the response can be of any type, and it allows us to return different types of responses based on the situation.
// For example, we can return a list of journal entries when the request is successful, or we can return an error message when something goes wrong.
// This flexibility is useful for handling various scenarios in our API.

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            userService.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?>updateUser(@RequestBody User user,@PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("Userid/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id){
        Optional<User>user = userService.getUserByID(id);
        if(user.isPresent()){
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
