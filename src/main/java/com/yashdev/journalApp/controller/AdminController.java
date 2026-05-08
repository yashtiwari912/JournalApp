package com.yashdev.journalApp.controller;


import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-all-users")
    private ResponseEntity<?>getAllUSers(){
        List<User> all = userService.getAll();

        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Since the /admin route is authenticated so only the authenticated ADMIN is allowed to add user , as we delibrately updated one user as admin in database
    @PostMapping("/create-admin-users")
    private ResponseEntity<?>addUser(@RequestBody User user){
        userService.saveAdminUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
