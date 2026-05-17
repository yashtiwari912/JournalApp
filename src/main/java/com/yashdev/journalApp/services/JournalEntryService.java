package com.yashdev.journalApp.services;


//Standard Architecture for Spring Boot Application
//Controller --> Service --> Repository --> Database

import com.yashdev.journalApp.entity.JournalEntry;
import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    //we did not implement it as spring implements it itself

    @Autowired
    private UserService userService;

    //@Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        //System.out.println("userName = '" + userName + "'");
        try {
            User user = userService.findByUserName(userName);
            //System.out.println("user: "+ user);
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    //@Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error ",e);
            throw new RuntimeException(e);
        }
        return removed;



    }

}
