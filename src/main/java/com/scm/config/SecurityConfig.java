package com.scm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.entities.User;

@Configuration
public class SecurityConfig {

    //user create using java code with memory service

    // @Bean
    // public UserDetailsService userDetailsService() {

    //     UserDetails user1 = User.withDefaultPasswordEncoder().withusername("admin").password("admin123").roles("ADMIN","USER").build();
    //     UserDetails user2 = User.withDefaultPasswordEncoder().withusername("user").password("user123").roles("USER").build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
    //     return inMemoryUserDetailsManager;
    // }}

    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        //user detail service

        daoAuthProvider.setUserDetailsService(null);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthProvider;
        
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        
    }

    




}
