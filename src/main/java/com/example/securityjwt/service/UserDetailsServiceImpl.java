package com.example.securityjwt.service;

import java.util.Collections;

import com.example.securityjwt.dao.repository.UserRepository;
import com.example.securityjwt.domain.MyUserDetails;
import com.example.securityjwt.support.exception.UserNotFoundException;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).map(
                u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue()))))
                .orElseThrow(() -> new UserNotFoundException(email));
    }

}
