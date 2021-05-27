package com.chb.learning.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class Test {

    @PostMapping("/loginbyusername")
    public String returnString(){
        return "Hello world";
    }

    public static void main(String[] args) {
        int result = get();
        System.out.println(result);
    }

    private static Integer get(){
        return null;
    }
}
