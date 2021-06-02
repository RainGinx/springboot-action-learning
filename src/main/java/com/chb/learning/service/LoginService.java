package com.chb.learning.service;

import com.chb.learning.entity.vo.UserVo;

/**
 * @author caihongbin
 * @date 2021/6/2 10:05
 */
public interface LoginService {

    UserVo getUserByName(String name);
}
