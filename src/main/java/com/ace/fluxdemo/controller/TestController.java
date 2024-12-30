package com.ace.fluxdemo.controller;

import com.ace.fluxdemo.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {




    @GetMapping("/test")
    public Mono<ResponseEntity<TestResponse>> testController(){

        return Mono.justOrEmpty(ResponseEntity.ok(TestResponse.builder().message("successfull").build()));
    }
}
