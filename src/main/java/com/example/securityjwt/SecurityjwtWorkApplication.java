package com.example.securityjwt;

import com.example.securityjwt.dao.repository.UserRepository;
import com.example.securityjwt.domain.User;
import com.example.securityjwt.support.enums.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SecurityjwtWorkApplication {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SecurityjwtWorkApplication.class, args);
	}


	@Bean
	public CommandLineRunner sampleJPACommandLineRunner(UserRepository repository) {
		return (args) -> {
			// save a user
			repository.save(new User("admin@example.com", passwordEncoder.encode("admin123"), UserRole.ROLE_ADMIN));
			repository.save(new User("user@example.com", passwordEncoder.encode("user123"), UserRole.ROLE_USER));
			repository.save(new User("guest@example.com", passwordEncoder.encode("guest123"), UserRole.ROLE_USER));

			// fetch all user
			log.info("-------- user found with findAll()-------");
			for (User user : repository.findAll()) {
				log.info(user.toString());
			}
		};
	}
}
