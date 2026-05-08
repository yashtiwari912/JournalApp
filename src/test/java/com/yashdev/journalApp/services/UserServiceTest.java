package com.yashdev.journalApp.services;

import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //This annoatation tell that this method will run first before other testcases
    //like initailization of data or any other setup
//    @BeforeEach
//    void setUp(){
//
//    }

    //@BeforeAll//This annotation tell that this method will run only once before all the testcases
    //similary there are @AfterEach and @AfterAll annotation which will run after the testcases


//    @Disabled
//    @Test
//    public void testBuUserName(){
//        //Unit Testing for UserRepository
//        //assertNotNull(userRepository.findByUserName("Ram"));
//        User user = userRepository.findByUserName("Ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
//    }

//    @ValueSource(strings = {
//            "Ram",
//            "shyam",
//            "Yash"
//    })
    @ArgumentsSource(UserArgumentProvider.class)
    @ParameterizedTest
    public void testSaveNewUser(User user){
        //assertNotNull(userRepository.findByUserName(name));
        assertTrue(userService.saveNewUser(user));
    }
    @CsvSource({
            // a,b,expected
            "1, 2, 3",
            "2, 3, 5",
            "3, 4, 7"
    })
    @ParameterizedTest
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
