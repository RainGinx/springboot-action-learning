package com.chb.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author caihongbin
 * @date 2021/6/2 9:58
 */
@Data
@AllArgsConstructor
public class Role {

    private Long id;
    private String name;

    private Set<Permission> permissions;

}
