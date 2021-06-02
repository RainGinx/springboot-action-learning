package com.chb.learning.service.impl;

import com.chb.learning.entity.vo.UserVo;
import com.chb.learning.mapper.UserMapper;
import com.chb.learning.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author caihongbin
 * @date 2021/6/2 10:05
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserVo getUserByName(String name) {
        return userMapper.getByName(name);
    }
}
