package com.chb.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author caihongbin
 * @date 2021/5/29 14:56
 */
@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String password;

    private Set<Role> roles;
}