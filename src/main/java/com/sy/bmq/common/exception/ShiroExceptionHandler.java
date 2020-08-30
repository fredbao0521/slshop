package com.sy.bmq.common.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShiroExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        e.printStackTrace();
        if(e instanceof UnauthorizedException){
            return "html/error";
        }else if(e instanceof AuthorizationException){
            return "html/fail";
        }else if(e instanceof IncorrectCredentialsException){
            return "html/fail";
        }
        return "html/error";
    }
}
