package com.chb.learning.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.chb.learning.auth.entity.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caihongbin
 * @date 2021/6/2 11:45
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public BaseResponse<Object> TokenExpiredException(Throwable ex){
        BaseResponse<Object> ret = new BaseResponse<>();
        ret.setErrCode(401);
        ret.setData("登录过期");
        ret.setMsg(ex.getMessage());
        return ret;
    }

    // 捕捉shiro的异常
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Object handleShiroException(Throwable e) {
        BaseResponse<Object> ret = new BaseResponse<>();
        ret.setErrCode(401);
        ret.setMsg(e.getMessage());
        return ret;
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object globalException(Throwable ex) {
        BaseResponse<Object> ret = new BaseResponse<>();
        ret.setErrCode(401);
        ret.setData("认证不通过");
        ret.setMsg(ex.getMessage());
        return ret;
    }
}
