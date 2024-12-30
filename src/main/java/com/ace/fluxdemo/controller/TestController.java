package com.ace.fluxdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {




    @GetMapping("/test")
    public Mono<ResponseEntity<String>> testController(){

        return Mono.justOrEmpty(ResponseEntity.ok("Successfull"));
    }
}
