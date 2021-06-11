package com.chb.learning.auth.config;

import com.chb.learning.entity.vo.PermissionVo;
import com.chb.learning.entity.vo.RoleVo;
import com.chb.learning.entity.vo.UserVo;
import com.chb.learning.service.LoginService;
import com.chb.learning.auth.entity.JwtToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author caihongbin
 * @date 2021/5/29 18:49
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 限定这个 Realm 只处理我们自定义的 JwtToken
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token !=null && JwtToken.class.isAssignableFrom(token.getClass());
    }

    /**
     * 此处的 SimpleAuthenticationInfo 可返回任意值，密码校验时不会用到它
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authcToken;
        // 从 JwtToken 中获取当前用户
        String username = jwtToken.getPrincipal().toString();
        if (username == null) {
            throw new AccountException("JWT token参数异常！");
        }
        // 查询数据库获取用户信息，此处使用 Map 来模拟数据库
        UserVo user = loginService.getUserByName(username);

        // 用户不存在
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        // 用户被锁定
        if (user.getLocked()) {
            throw new LockedAccountException("该用户已被锁定,暂时无法登录！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户
        UserVo currentUser = (UserVo) SecurityUtils.getSubject().getPrincipal();
        // UserEntity currentUser = (UserEntity) principals.getPrimaryPrincipal();
        // 查询数据库，获取用户的角色信息
        Set<RoleVo> roleSet = currentUser.getRoles();
        Set<String> roles = roleSet.stream().map(RoleVo::getName).collect(Collectors.toSet());
        // 查询数据库，获取用户的权限信息
        Set<PermissionVo> permissionSet = new HashSet<>();
        roleSet.stream().map(RoleVo::getPermissions).forEach(p->permissionSet.addAll(p));
        Set<String> perms = permissionSet.stream().map(PermissionVo::getName).collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(perms);
        return info;
    }

}
