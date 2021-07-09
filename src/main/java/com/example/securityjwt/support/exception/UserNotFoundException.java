package com.example.securityjwt.support.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userEmail) {
        super(userEmail + " NotFoundException");
    }

}
