package com.example.securityjwt.service;

import java.util.List;

import com.example.securityjwt.dao.repository.UserRepository;
import com.example.securityjwt.domain.SignUp;
import com.example.securityjwt.domain.User;
import com.example.securityjwt.support.enums.UserRole;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User signUp(final SignUp signUpDTO) {
        final User user = User.builder().email(signUpDTO.getEmail()).pw(passwordEncoder.encode(signUpDTO.getPw()))
                .role(UserRole.ROLE_USER).build();

        return userRepository.save(user);
    }

    public boolean isEmailDuplicated(final String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
