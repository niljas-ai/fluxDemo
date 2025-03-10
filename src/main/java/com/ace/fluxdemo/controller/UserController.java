package com.ace.fluxdemo.controller;

import com.ace.fluxdemo.model.response.UserResponse;
import com.ace.fluxdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/user/{userId}")
    public Mono<ResponseEntity<UserResponse>> getUser(@PathVariable String userId) {

        return userService.getUser(userId)
                .map(userResponse ->
                        ResponseEntity.ok(userResponse))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public Flux<UserResponse> getAllUsers() {
        return userService.getUsers();
    }
}
