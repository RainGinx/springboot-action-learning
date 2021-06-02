package com.chb.learning.controller;

import com.chb.learning.entity.po.User;
import com.chb.learning.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class Test {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/loginbyusername")
    public String returnString(){
        return "Hello world";
    }

    @GetMapping("list")
    public List<User> list(){
        List<User> list= userMapper.selectList(null);
        return list;
    }

    public static void main(String[] args) {
        int result = get();
        System.out.println(result);
    }

    private static Integer get(){
        return null;
    }
}
