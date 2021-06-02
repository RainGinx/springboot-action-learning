package com.chb.learning.service.impl;

import com.chb.learning.entity.Permission;
import com.chb.learning.entity.Role;
import com.chb.learning.entity.User;
import com.chb.learning.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author caihongbin
 * @date 2021/6/2 10:05
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUserByName(String name) {
        Permission permissions1 = new Permission(1L, "query");
        Permission permissions2 = new Permission(2L, "add");
        Set<Permission> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role(1L, "admin", permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User(1L, "wsl", 18,"123456","123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getName(), user);

        Set<Permission> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        Role role1 = new Role(2L, "user", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User(2L, "zhangsan", 20,"123456","123456", roleSet1);
        map.put(user1.getName(), user1);
        return map.get(name);
    }
}
