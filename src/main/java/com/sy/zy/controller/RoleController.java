package com.sy.zy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.bmq.model.Role;
import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.RoleService;
import com.sy.zy.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;


    //查询所有集合分页
    @RequiresPermissions("/role/selectall.do")
    @RequestMapping("/selectall.do")
    public BaseResult findAll(Integer page, Integer limit) throws Exception {
        BaseResult baseResult = new BaseResult();
        List<Role> select = roleService.select(limit, page);
        PageInfo<Role> of = PageInfo.of(select);
        long total = of.getTotal();
        if (!select.isEmpty()) {
            baseResult.setCode(0);
            baseResult.setCount((int) total);
            baseResult.setData(select);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }

    //删除角色，关联用户表，存在就不删除
    @RequestMapping("/delete.do")
    public BaseResult deletebyId(int id) throws Exception {
        BaseResult baseResult = new BaseResult();
        User user=new User();
        user.setRoleId(id);
        List<User> users = userService.selectAll(user);
        if(users.size()==0){
            Integer delete = roleService.delete(id);
            if (delete > 0) {
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

    @RequestMapping("/update.do")
    public BaseResult update(Role role) throws Exception {
        BaseResult baseResult = new BaseResult();
        Integer update = roleService.update(role);
        if (update > 0) {
            baseResult.setCode(0);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }

    @RequestMapping("/insert.do")
    public BaseResult insert(Role role) throws Exception {
        BaseResult baseResult = new BaseResult();
        role.setCreateDate(new Date());
        role.setCreateBy("admin");
        Integer update = roleService.insert(role);
        if (update > 0) {
            baseResult.setCode(0);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }



    @RequestMapping("/selectcount.do")
    public BaseResult selectCount(String roleName) throws Exception {
        BaseResult baseResult = new BaseResult();
        if(roleName!=("管理员")){
            Integer integer = userService.selectCount(roleName);
            baseResult.setCount(integer);
            baseResult.setData(roleName);
            baseResult.setCode(0);
        }
        return baseResult;
    }

}
