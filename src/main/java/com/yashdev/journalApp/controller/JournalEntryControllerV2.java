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
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry>all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryByID(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
        JournalEntry oldJournalEntry = journalEntryService.getJournalEntryByID(id).orElse(null);
        if(oldJournalEntry != null){
            oldJournalEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldJournalEntry.getTitle() );
            oldJournalEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldJournalEntry.getContent());
            //I have updated oldJounalEntry with new Values and then saved them in db

            journalEntryService.saveEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }

}
