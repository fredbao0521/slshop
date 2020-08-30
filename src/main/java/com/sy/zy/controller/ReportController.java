package com.sy.zy.controller;


import com.sy.bmq.model.Report2;
import com.sy.bmq.model.base.BaseResult;
import com.sy.zy.service.ReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    //查询同种type的多条
   @RequiresPermissions("/report/selectall.do")
    @RequestMapping("/selectall.do")
    public BaseResult select(Integer type)throws Exception{
        BaseResult baseResult=new BaseResult();
        List<Report2> report2s = reportService.find(type);
        System.out.println("445454545545"+report2s);
        baseResult.setData(report2s);
        baseResult.setCode(0);
        return baseResult;

    }

}
