package com.scm.config;

import java.io.IOException;
import java.nio.channels.UnsupportedAddressTypeException;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.scm.services.impl.SecurityCustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Configuration
public class SecurityConfig {
    
    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        //user detail service

        daoAuthProvider.setUserDetailsService(userDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthProvider;
        
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //urls pe jyege konse public and konse private
        httpSecurity.authorizeHttpRequests(authorize ->{
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //form defualt login
        //agar kuch bhi change krna hua to hame yaha ayega login se related
        //httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login")  
                .loginProcessingUrl("/authenticate")
                .successForwardUrl("/user/dashboard")
                // .failureForwardUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password");
            
                // formLogin.failureHandler(new AuthenticationFailureHandler() {
                //     @Override
                //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                //             AuthenticationException exception) throws IOException, ServletException {
                    
                //                 throw new UnsupportedAddressTypeException("Invalid username or password");
                //     }
                    
                // });
                // formLogin.successHandler(new AuthenticationSuccessHandler() {
                //     @Override
                //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                //             Authentication authentication) throws IOException, ServletException {
                //         response.sendRedirect("/user/dashboard");
                        
                //     }
                // })



        });
        //iske bina logout url nhi chalega
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(logout -> {
            logout.logoutUrl("/do-logout")
                .logoutSuccessUrl("/login?logout=true");
        });




        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        
    }

    




}
