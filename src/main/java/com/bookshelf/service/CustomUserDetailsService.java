package com.bookshelf.service;


import com.bookshelf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepositpory;

    @Override
    public UserDetails loadUserByUsername(String username){
        com.bookshelf.entity.User user = userRepositpory.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:"+username));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
