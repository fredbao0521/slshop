package com.sy.zy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping("/login.do")
    public BaseResult toIndex(HttpServletRequest request, HttpSession session) {
        BaseResult baseResult = new BaseResult();
        String remoteUser = request.getRemoteUser();
        session.setAttribute("sessionUser", remoteUser);
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setMsg(BaseResult.MSG_SUCCESS);
        baseResult.setData(remoteUser);
        return baseResult;
    }


    /*退出登录跳到登录页面*/

    @RequestMapping("/loginout.do")
    public BaseResult logout() {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(0);
        return baseResult;
    }


    /*增加注册会员*/
    /*没写完*/
    @RequestMapping("/add.do")
    public BaseResult addVip(User user,HttpSession session) throws Exception {
        user.setCreateTime(new Date());
        user.setRoleId(2);
        user.setPassword2("0000");
        user.setRoleName("注册会员");
        user.setIsStart(0);
        User user1=new User();
        user1.setUsername((String) session.getAttribute("sessionUser"));
        List<User> users1 = userService.selectAll(user1);
        user.setReferId(users1.get(0).getId());
        user.setReferCode((String) session.getAttribute("sessionUser"));

        user.setLastUpdateTime(new Date());
        System.out.println(user.getEmail() + "9999");
        BaseResult baseResult = new BaseResult();

        List<User> users = userService.selectAll(user);
        if (user==null){
            Integer insert = userService.Insert(user);
            if (insert > 0) {
                baseResult.setCode(0);
                return baseResult;
            } else {
                baseResult.setCode(1);
                return baseResult;
            }
        }else {
            baseResult.setMsg("failed");
            return baseResult;
        }

    }

    /*显示所有的会员用户*/
    @RequestMapping("/viplist.do")
    public BaseResult findall(User user, Integer page, Integer limit) throws Exception {
        BaseResult baseResult = new BaseResult();
      List<User> users = userService.selectPage(user,limit,page);
        PageInfo pageInfo=PageInfo.of(users);
        long total = pageInfo.getTotal();
        System.out.println(users + "..5555...");
        if (users != null) {
            baseResult.setData(users);
            baseResult.setCode(0);
            baseResult.setCount((int)total);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }


    }

    /*修改密码*/
    @RequestMapping("/changepwd.do")
    public BaseResult changepwd(User user) throws Exception {
        BaseResult baseResult = new BaseResult();
        Integer update = userService.updatepwd(user);
        if (update > 0) {
            baseResult.setCode(0);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }


    @RequestMapping("/delete.do")
    public BaseResult del(int id)throws Exception{
        BaseResult baseResult = new BaseResult();
        Integer delete = userService.delete(id);
        if (delete>0){
            baseResult.setCode(0);
        }else {
            baseResult.setCode(1);
        }
       return baseResult;
    }


    /*修改用户信息*/
    @RequestMapping("/updateperson.do")
    public BaseResult midify(User user)throws Exception{
        BaseResult baseResult = new BaseResult();
        Integer integer = userService.updateMess(user);
        if(integer>0){
            baseResult.setCode(0);
        }else {
            baseResult.setCode(1);
        }
        return baseResult;
    }

/*正式用户*/
    @RequestMapping("/select.do")
public BaseResult findvip(User user, Integer page, Integer limit) throws Exception {
    BaseResult baseResult = new BaseResult();
        System.out.println(user);
    List<User> users = userService.select(user, page, limit);
        System.out.println(users+"././/");
    PageInfo pageInfo=PageInfo.of(users);
    long total = pageInfo.getTotal();

    if (users != null) {
        baseResult.setData(users);
        baseResult.setCode(0);
        baseResult.setCount((int)total);
        return baseResult;
    } else {
        baseResult.setCode(1);
        return baseResult;
    }


}

}
