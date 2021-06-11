package com.chb.learning.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chb.learning.auth.entity.JwtToken;
import com.chb.learning.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * @author caihongbin
 * @date 2021/6/10 11:00
 */
// FIXME: 2021/6/11 暂时不使用这种  token校验放到filter中
@Slf4j
public class JwtCredentialsMatcher implements CredentialsMatcher {

    /**
     * JwtCredentialsMatcher只需验证JwtToken内容是否合法
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getToken();
        String username = jwtToken.getPrincipal().toString();
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error(e.getMessage());
        }
        return false;
    }

}