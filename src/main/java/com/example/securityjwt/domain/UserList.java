package com.example.securityjwt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserList {

    private final List<User> userList;

}
