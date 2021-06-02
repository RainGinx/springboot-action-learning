package com.chb.learning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chb.learning.entity.po.User;
import com.chb.learning.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    public UserVo getByName(@Param("name")String name);
}
