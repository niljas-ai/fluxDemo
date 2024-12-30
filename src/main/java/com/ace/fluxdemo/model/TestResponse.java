package com.ace.fluxdemo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestResponse {

    private String message;
}
