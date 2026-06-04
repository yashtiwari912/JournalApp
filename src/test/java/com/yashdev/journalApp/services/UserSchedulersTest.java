package com.yashdev.journalApp.services;

import com.yashdev.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulersTest {

        @Autowired
        private UserScheduler userScheduler;


        @Test
        public void testFetchUsersAndSendSaMail(){
                System.out.println("Testing fetchUsersAndSendSaMail...");
                userScheduler.fetchUsersAndSendSaMail();
        }
}

