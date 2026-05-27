package com.yashdev.journalApp.scheduler;

import com.yashdev.journalApp.cache.AppCache;
import com.yashdev.journalApp.entity.JournalEntry;
import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepositoryImpl;
import com.yashdev.journalApp.services.EmailService;
import com.yashdev.journalApp.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN") // Every Sunday at 9 AM
    //the @Scheduled annotation is used to schedule the execution of the fetchUserAndSendSaMail() method. The cron expression "0 0 9 * * SUN" specifies that the method should be executed every Sunday at 9 AM.
    //@Scheduled(cron = "0 * *  ? * *") // Every minute
    public void fetchUserAndSendSaMail(){
            List<User> users = userRepository.getUserForSA();
            for(User user :users){
                List<JournalEntry> journalEntries = user.getJournalEntries();
                List<String> filteredList = journalEntries.stream()
                        .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());

                String entry = String.join(" ",filteredList);
                String sentiment = sentimentAnalysisService.getSentiment(entry);
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7 days ",sentiment);
                //System.out.println("Email sent to: "+user.getEmail());
            }
    }

    @Scheduled(cron = "0 0/10 *  ? * *")//every 10 min
    public void clearAppCache(){
        appCache.init();
    }
}
