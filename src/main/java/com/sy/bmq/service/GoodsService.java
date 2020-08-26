package com.sy.bmq.service;

import com.github.pagehelper.PageInfo;
import com.sy.bmq.model.GoodsInfo;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface GoodsService {

    PageInfo findByPage() throws Exception;

    List<GoodsInfo> findAllGoods(int pageNum, int pageSize, String goodsSN, String goodsName, String note, BaseResult result) throws Exception;

    int removeGood(Integer id) throws Exception;

    int addGood(GoodsInfo goodsInfo) throws Exception;

    int updateGood(GoodsInfo goodsInfo) throws Exception;
}
