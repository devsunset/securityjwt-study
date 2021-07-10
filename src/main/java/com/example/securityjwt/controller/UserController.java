package com.example.securityjwt.controller;

import com.example.securityjwt.domain.SignUp;
import com.example.securityjwt.domain.UserList;
import com.example.securityjwt.service.UserService;
import com.example.securityjwt.support.utils.JwtTokenUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(@RequestBody final SignUp signUp) {
        return userService.isEmailDuplicated(signUp.getEmail()) ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(JwtTokenUtil.generateJwtToken(userService.signUp(signUp)));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<UserList> list() {
        final UserList userListResponseDTO = UserList.builder().userList(userService.findAll()).build();

        return ResponseEntity.ok(userListResponseDTO);
    }

}
