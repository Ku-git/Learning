package com.ku.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${test.value}")
    private String value;
    @Value("${test.val2}")
    private String value2;

    @GetMapping("/val")
    public String test() {
        return value;
    }

    @GetMapping("/val2")
    public String test2() {
        return value2;
    }

    @GetMapping("/")
    public String empty() {
        return "test";
    }
}
