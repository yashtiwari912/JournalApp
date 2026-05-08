package com.yashdev.journalApp.config;
import com.yashdev.journalApp.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;


//    private final UserDetailsServiceImpl userDetailsService;
//
//    public SpringSecurity(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //  Authentication Provider (modern replacement)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());//Hash password is stored and the password encoder is used to compare the hashed password with the raw password during authentication
        return authProvider;
    }

    // Security config
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(request -> request
                        //.requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/journal/**" , "/user/**").authenticated() // Only journal endpoints require authentication
                        .requestMatchers("/admin/**").hasRole("ADMIN")// Only admin endpoints require ADMIN role
                        .anyRequest().permitAll()
                )
                .authenticationProvider(authenticationProvider()) //  IMPORTANT
                .httpBasic(Customizer.withDefaults())
                //.csrf(csrf -> csrf.disable())//cross site request forgery is a type of attack that occurs when a malicious website or application tricks a user into performing an action on another website where they are authenticated. In our case, since we are building a REST API that is likely to be consumed by various clients (like mobile apps, frontend applications, etc.), we can disable CSRF protection as it is not necessary for stateless APIs. However, if you were building a traditional web application with server-side rendering, you would want to keep CSRF protection enabled.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }
}