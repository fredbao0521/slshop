package com.sy.bmq.controller;

import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * shiro认证
 */
@RestController
public class ShiroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroController.class);

    @Autowired
    private UserService service;

    /**
     * 登录认证
     * @param user
     * @param captcha
     * @param session
     * @return
     */
    @RequestMapping("/login.do")
    public BaseResult login(User user, String captcha, HttpSession session) {

        BaseResult baseResult = new BaseResult();
        //1.验证码比较
        String text = (String)session.getAttribute("captcha");
        if(!text.toLowerCase().equals(captcha)){
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("验证码不正确");
            return baseResult;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        try {
            subject.login(token);
            //用户放入session
            User findUser = service.findByUsername((String) subject.getPrincipal());
            session.setAttribute("sessionUser", findUser);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg("登录成功");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage(), e);
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg("用户名或密码错误");
        }
        return baseResult;
    }

    /**
     * 会话验证
//     * @param session
     * @return
     */
//    @RequestMapping("/session.do")
//    public BaseResult findSessionUser(HttpSession session, HttpServletRequest request){
//        BaseResult baseResult = new BaseResult();
//        User sessionUser = (User)session.getAttribute("sessionUser");
//        String remoteUser = request.getRemoteUser();
//        System.out.println("xxxxx"+remoteUser);
//        if(remoteUser==null){
//            baseResult.setMsg("请登录");
//            baseResult.setCode(BaseResult.CODE_FAILED);
//        }else{
//            baseResult.setMsg(BaseResult.MSG_SUCCESS);
//            baseResult.setCode(BaseResult.CODE_SUCCESS);
//            baseResult.setData(remoteUser);
//        }
//        return baseResult;
//    }
    @RequestMapping("/logout.do")
    public BaseResult logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setMsg(BaseResult.MSG_SUCCESS);
        return baseResult;
    }

}
