package com.example.securityjwt.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.securityjwt.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPw(String email, String pw);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
