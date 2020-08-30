package com.sy.zy.controller;


import com.sy.bmq.model.Func;
import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.AuthService;
import com.sy.zy.service.FuncService;
import com.sy.zy.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 权限操作
 */
@RestController
@RequestMapping("/func")
public class FuncController {

    @Autowired
    private FuncService service;
    @Autowired
    private UserService userService;
    @Autowired
    AuthService authService;

    @RequiresPermissions("/func/find.do")
    @RequestMapping("/find.do")
    public BaseResult findFunc(Integer id) throws Exception {
        System.out.println(id + "99090");
        Func byId = service.findone(id);
        BaseResult baseResult = new BaseResult();
        if (byId != null) {
            baseResult.setData(byId);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
        } else {
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg(BaseResult.MSG_FAILED);
        }

        return baseResult;
    }


    @RequestMapping("/add.do")
    @RequiresPermissions("/func/add.do")
    public BaseResult addFunc(Func func, HttpSession session) throws Exception {
        String user = (String) session.getAttribute("sessionUser");
        User user1 = new User();
        user1.setUsername(user);
        List<User> users = userService.selectAll(user1);
        func.setCreationTime(new Date());
        Integer save = service.save(func, users.get(0));
        BaseResult baseResult = new BaseResult();
        if (save != null && save != 0) {
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        } else {
            baseResult.setMsg(BaseResult.MSG_FAILED);
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;
    }

    @RequestMapping("/modify.do")
    @RequiresPermissions("/func/modify.do")
    public BaseResult update(Func func) throws Exception {
        System.out.println(func.getFuncName() + "15414");
        BaseResult baseResult = new BaseResult();
        func.setCreationTime(new Date());
        Integer modify = service.modify(func);
        if (modify > 0) {
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        } else {
            baseResult.setMsg(BaseResult.MSG_FAILED);
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;
    }


    @RequestMapping("/delete.do")
    @RequiresPermissions("/func/delete.do")
    public BaseResult delete(Func func) throws Exception {
        BaseResult baseResult = new BaseResult();
        Integer delete = authService.delete(func.getId());
        Integer integer = service.removeOne(func.getId());
        if (delete + integer >= 2) {
            System.out.println(1);
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
            baseResult.setCode(BaseResult.CODE_SUCCESS);
        } else {
            System.out.println(3);
            baseResult.setMsg(BaseResult.MSG_FAILED);
            baseResult.setCode(BaseResult.CODE_FAILED);
        }
        return baseResult;

    }


}
