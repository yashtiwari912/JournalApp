package com.yashdev.journalApp.services;


//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database

import com.yashdev.journalApp.entity.JournalEntry;
import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    //we did not implement it as spring implements it itself

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);//as currently we have not implemented auth
    }
    public List<JournalEntry>getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getJournalEntryByID(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
