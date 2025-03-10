package com.ace.fluxdemo.service;

import com.ace.fluxdemo.mapper.UserMapper;
import com.ace.fluxdemo.model.response.UserResponse;
import com.ace.fluxdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class userServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserResponse> getUser(String userId) {
        return userRepository
                .findById(getId(userId))
                .doOnNext(user -> log.info("User found: {}", user.getUsername())).doOnSuccess(user -> {
                    if (user != null) {
                        log.info("User found: {}", user.getUsername());
                    } else {
                        log.info("No user found with id {}", userId);
                    }
                }).map(UserMapper.INSTANCE::toResponse)
                .doOnError(throwable -> log.error("Get User DB error : ", throwable))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));

    }

    @Override
    public Flux<UserResponse> getUsers() {
        log.info("indside get user");
        return userRepository.findAll()
                .doOnSubscribe(s -> log.info("Subscribed to findById"))
                .doOnNext(user -> log.info("User found: {}", user.getUsername()))
                .doOnError(throwable -> log.error("Get Users DB error : ", throwable))
                .map(UserMapper.INSTANCE::toResponse)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")));
    }

    private static long getId(String userId) {
        return Long.parseLong(userId);
    }
}
