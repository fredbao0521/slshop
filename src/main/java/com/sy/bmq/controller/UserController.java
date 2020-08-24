package com.sy.bmq.controller;


import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @CrossOrigin
    @RequestMapping("/login.do")
    public BaseResult findSessionUser(HttpServletRequest request){
        BaseResult baseResult = new BaseResult();
//        User sessionUser = (User)session.getAttribute("sessionUser");
        String remoteUser = request.getRemoteUser();
        System.out.println("xxxxx"+remoteUser);
        if(remoteUser==null||remoteUser.equals("")){
            baseResult.setMsg("请登录");
            baseResult.setCode(BaseResult.CODE_FAILED);
        }else{
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setData(remoteUser);
        }
        return baseResult;
    }



}
