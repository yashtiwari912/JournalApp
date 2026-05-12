package com.yashdev.journalApp.services;

import com.yashdev.journalApp.entity.User;
import com.yashdev.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

//@SpringBootTest
@ActiveProfiles("dev")
public class UserDetailsServiceImplTest {

    //@Autowired
    @InjectMocks//InjectMocks automatically creates instance of this service
    private UserDetailsServiceImpl userDetailsService;
    //Since we don't want to use spring application context so we will remove @SpringBootTest and we will use @MockBean to mock the UserRepository and we will use @InjectMocks to inject the mocked UserRepository into the UserDetailsServiceImpl

    //@MockitoBean
    @Mock //This annotation is used to create a mock instance of the UserRepository class.
    // It tells Mockito to create a mock object for the UserRepository interface, which will be used in the test cases to simulate the behavior of the actual UserRepository without needing to connect to a real database.
    //we replaced @MockBean with @Mock because we are not using spring application context
    // and at that time we were using @Autowired and @MockBean together which is not correct because @MockBean is used to mock a bean in the spring application context and @Autowired is used to inject a bean from the spring application context
    // so we will replace @MockBean with @Mock and we will use @InjectMocks to inject the mocked UserRepository into the UserDetailsServiceImpl
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        //since userRepository is not initialized as we are not using springboot test and we are using @Mock annotation to create a mock instance of the UserRepository class,
        // we need to initialize the mocks before each test case.
        // We can do this by calling MockitoAnnotations.initMocks(this) in the setUp method, which will initialize all the mocks annotated with @Mock in the test class.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        Mockito.when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("password").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }

}
