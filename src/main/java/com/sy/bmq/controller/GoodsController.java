package com.sy.bmq.controller;


import com.sy.bmq.model.GoodsInfo;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/listgoods.do")
    public BaseResult findAllGoods(int limit, int page, String goodsSN,String goodsName,String note, BaseResult result){
        System.out.println(goodsSN+","+goodsName+","+note);
        List<GoodsInfo> allGoods = null;
        try {
            allGoods = goodsService.findAllGoods(page, limit,goodsSN,goodsName,note, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setPage(page);
        result.setLimit(limit);
        for (int i = 0; i < allGoods.size(); i++) {
            System.out.println(allGoods.get(i).toString());
        }
        result.setData(allGoods);
        result.setCode(BaseResult.CODE_SUCCESS);
        return result;
    }

    @RequestMapping("/deletegood.do")
    public BaseResult removeGood(Integer id){
       BaseResult result = new BaseResult();
        try {
            int i = goodsService.removeGood(id);
            if (i>0){
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("删除成功");
            }else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
