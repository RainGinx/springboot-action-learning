package com.chb.learning.controller;

import com.chb.learning.auth.entity.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author caihongbin
 * @date 2021/6/11 18:42
 */

@RestController
@RequestMapping("/error")
public class FilterErrorController {

    @GetMapping(path = "/unauthorized/{message}")
    public BaseResponse<String> unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return new BaseResponse<>(401, "error: "+message,null);
    }

}