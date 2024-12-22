package com.springsecurity.stay_ease_jwt.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.stay_ease_jwt.entity.User;
import com.springsecurity.stay_ease_jwt.repository.IUserRepository;


@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with given email Id : " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().name().equals("ADMIN") ?
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN")) :
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}