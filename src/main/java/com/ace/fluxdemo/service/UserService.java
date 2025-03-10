package com.ace.fluxdemo.service;

import com.ace.fluxdemo.model.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserResponse> getUser(String Userid);

    Flux<UserResponse> getUsers();
}
