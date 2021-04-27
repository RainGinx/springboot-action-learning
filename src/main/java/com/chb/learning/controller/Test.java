package com.chb.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/s")
    public String returnString(){
        return "Hello world!";
    }
}
