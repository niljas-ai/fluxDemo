package com.ace.fluxdemo.service;

import com.ace.fluxdemo.mapper.UserMapper;
import com.ace.fluxdemo.repository.UserRepository;
import com.ace.fluxdemo.service.utils.TestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UserServiceimplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getUserTest() {

        Mockito.when(userMapper.toResponse(TestData.getUser()))
                .thenReturn(TestData.getUserResponse());

        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(TestData.getUser()));

        StepVerifier.create(userService.getUser("1"))
                .assertNext(userResponse -> {
                    Assertions.assertThat(userResponse.getUsername())
                            .isEqualTo("alice");
                }).verifyComplete();

        Mockito.verify(userRepository).findById(ArgumentMatchers.anyLong());
        Mockito.verify(userMapper).toResponse(TestData.getUser());
    }

    @Test
    public void getUserMonoEmptyTest() {

        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());
        StepVerifier.create(userService.getUser("5")).expectError(ResponseStatusException.class).verify();

        Mockito.verify(userRepository).findById(ArgumentMatchers.anyLong());
        Mockito.verifyNoInteractions(userMapper);
    }

    @Test
    public void getUserMonoErrorTest() {
        Mockito.when(userRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.error(new RuntimeException("connection error")));

        StepVerifier.create(userService.getUser("7"))
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException && throwable.getMessage().equals("connection error"));

        Mockito.verify(userRepository).findById(ArgumentMatchers.anyLong());
        Mockito.verifyNoInteractions(userMapper);
    }

}
