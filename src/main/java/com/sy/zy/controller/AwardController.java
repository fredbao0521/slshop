package com.sy.zy.controller;

import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/award")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @RequestMapping("/select.do")
    public BaseResult select()throws Exception{
        BaseResult baseResult=new BaseResult();
        List<AccountDetail> select = awardService.select();
        baseResult.setData(select);
        baseResult.setCode(0);
        return baseResult;
    }




}
