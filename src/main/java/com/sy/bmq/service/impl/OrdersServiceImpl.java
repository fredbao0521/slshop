package com.sy.bmq.service.impl;

import com.sy.bmq.mapper.GoodsMapper;
import com.sy.bmq.mapper.OrdersMapper;
import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.Shopcart;
import com.sy.bmq.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int creatCart(Shopcart shopcart) throws Exception {
        return ordersMapper.creatCart(shopcart);
    }

    @Override
    public Shopcart findCart(String username) throws Exception {
        return ordersMapper.findCart(username);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addCartGood(CartGoods cartGoods) throws Exception {
        int i = goodsMapper.updateGoodNum(cartGoods.getGoodsNum(),cartGoods.getGoodsId());
        int i1 = ordersMapper.addCartGood(cartGoods);
        return i+i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateCartGood(CartGoods cartGoods) throws Exception {
        return updateCartGood(cartGoods);
    }
}
