package com.sy.zy.controller;

import com.github.pagehelper.PageInfo;
import com.sy.bmq.model.DataDictionary;
import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.DictionaryService;
import com.sy.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dic")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    UserService userService;


    @RequestMapping("/selectall.do")
    public BaseResult findAll(Integer page, Integer limit) throws Exception {
        BaseResult baseResult = new BaseResult();
        List<DataDictionary> select = dictionaryService.select(limit, page);
        PageInfo<DataDictionary> of = PageInfo.of(select);
        long total = of.getTotal();
        if (!select.isEmpty()) {
            baseResult.setCount((int) total);
            baseResult.setCode(0);
            baseResult.setData(select);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }

    @RequestMapping("/delete.do")
    public BaseResult deletebyId(int id) throws Exception {
        BaseResult baseResult = new BaseResult();
        DataDictionary dataDictionary=new DataDictionary();
        dataDictionary.setId(id);
        List<DataDictionary> dictionaryService2 = dictionaryService.find2(dataDictionary);
        DataDictionary dataDictionary1 = dictionaryService2.get(0);
        String valueName=dataDictionary1.getValueName();
        System.out.println(valueName+"/////"+id);
        User user=new User();
        user.setCardTypeName(valueName);
        List<User> users = userService.selectAll(user);
        System.out.println(users.get(0).toString());
        if (users.get(0)==null){
            Integer delete = dictionaryService.delete(id);
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
    public BaseResult update(DataDictionary role) throws Exception {
        BaseResult baseResult = new BaseResult();
        DataDictionary select =dictionaryService.find1(role.getTypeCode(),role.getValueId());
        if (select==null){
            Integer update = dictionaryService.update(role);
            if (update > 0) {
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

    @RequestMapping("/insert.do")
    public BaseResult insert(DataDictionary role) throws Exception {
        BaseResult baseResult = new BaseResult();
        DataDictionary select =dictionaryService.find1(role.getTypeCode(),role.getValueId());
        if (select==null){
            Integer update = dictionaryService.update(role);
            if (update > 0) {
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




    @RequestMapping("/find.do")
    public BaseResult find(DataDictionary dataDictionary) throws Exception {
        BaseResult baseResult = new BaseResult();
        List<DataDictionary> select = dictionaryService.find2(dataDictionary);
        if (!select.isEmpty()) {
            baseResult.setCode(0);
            baseResult.setData(select);
            return baseResult;
        } else {
            baseResult.setCode(1);
            return baseResult;
        }
    }

}
