package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.bmq.mapper.GoodsMapper;
import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.GoodsInfo;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo findByPage() throws Exception {
        return null;
    }

    @Override
    public GoodsInfo selectById(Integer id) throws Exception {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<GoodsInfo> findAllGoods(int pageNum, int pageSize,String goodsSN,String goodsName,String note, BaseResult result) throws Exception {
        List<GoodsInfo> goodsInfos = goodsMapper.selectWithWhere(goodsName,goodsSN,note);
        result.setCount(goodsInfos.size());
        PageHelper.startPage(pageNum, pageSize);
        return goodsMapper.selectWithWhere(goodsName,goodsSN,note);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int removeGood(Integer id) throws Exception {
        return goodsMapper.deleteGood(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addGood(GoodsInfo goodsInfo) throws Exception {
        return goodsMapper.addGood(goodsInfo);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateGood(GoodsInfo goodsInfo) throws Exception {
        return goodsMapper.updateGood(goodsInfo);
    }

    @Override
    public List<CartGoods> selectByCartId(Integer cartId) throws Exception {
        return goodsMapper.selectByCartId(cartId);
    }
}
