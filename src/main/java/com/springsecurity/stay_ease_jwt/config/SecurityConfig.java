package com.springsecurity.stay_ease_jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(CustomUserDetailService userDetailService,CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.userDetailService = userDetailService;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @SuppressWarnings("removal")
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailService userDetailService) 
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailService)
                   .passwordEncoder(passwordEncoder)
                   .and()
                   .build();
    }   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .requestCache(RequestCacheConfigurer::disable) // Disable saving unauthenticated requests
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/error/**").permitAll()
                .requestMatchers("/users/register").permitAll()
                .requestMatchers("/books/**").hasRole("ADMIN")
                .requestMatchers("/books").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
