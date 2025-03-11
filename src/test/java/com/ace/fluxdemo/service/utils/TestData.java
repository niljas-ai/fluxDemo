package com.ace.fluxdemo.service.utils;

import com.ace.fluxdemo.entity.User;
import com.ace.fluxdemo.model.response.UserResponse;

public class TestData {

    public static User getUser() {
        return User.builder()
                .id(Long.parseLong("1"))
                .email("alic@123.gmail.com")
                .username("alice")
                .build();
    }

    public static UserResponse getUserResponse() {
        return UserResponse.builder()
                .username("alice")
                .email("alic@123.gmail.com")
                .build();

    }
}
