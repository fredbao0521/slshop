package com.sy.zy.controller;


import com.sy.bmq.model.Menu;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 菜单操作
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    //需要管理员权限
    @RequiresPermissions("/menu/find.do")
    @RequestMapping("/find.do")
    public BaseResult findMenus(Integer roleId) throws Exception{
        List<Menu> list = service.findByRoleId(roleId);
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(BaseResult.CODE_SUCCESS);
        baseResult.setMsg(BaseResult.MSG_SUCCESS);
        baseResult.setData(list);
        return baseResult;
    }

    //更新角色对应权限
    @RequiresPermissions("/menu/modify.do")
    @RequestMapping("/modify.do")
    public BaseResult modifyMenus(Integer roleId, String str, HttpSession session) throws Exception{
        String[] split = str.split("&");
        Integer[] funcId=new Integer[split.length];
        System.out.println(split+"121");
        for (int i =0; i <split.length ; i++) {
            String[] split1 = split[i].split("=");
            int i1 = Integer.parseInt(split1[1]);
            funcId[i]=i1;
        }
        String user = (String) session.getAttribute("sessionUser");
        Integer integer = service.modifyFuncByRole(roleId, funcId,user);
        BaseResult baseResult = new BaseResult();
        if(integer!=null && integer>0){
            baseResult.setCode(BaseResult.CODE_SUCCESS);
            baseResult.setMsg(BaseResult.MSG_SUCCESS);
        }else{
            baseResult.setCode(BaseResult.CODE_FAILED);
            baseResult.setMsg(BaseResult.MSG_FAILED);
        }
        return baseResult;
    }
}
