package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.sy.bmq.mapper.GoodsMapper;
import com.sy.bmq.mapper.OrdersMapper;
import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.OrderGoods;
import com.sy.bmq.model.OrderInfo;
import com.sy.bmq.model.Shopcart;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
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

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int deleteCartGood(CartGoods cartGoods) throws Exception {
        int i = ordersMapper.deleteCartGood(cartGoods);
        int i1 = goodsMapper.updateGoodNumUp(cartGoods.getGoodsNum(), cartGoods.getGoodsId());
        return i+i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insertOrder(OrderInfo orderInfo,String ids,String orderInfoId,String username) throws Exception {
        //在订单中添加选中的商品
        //根据id列表将商品查询出来
        List<CartGoods> cartGoods = ordersMapper.findCartGoods(ids);
        //循环插入
        int i2 = 0;
        //统计总价
        Double total = 0.0;
        for (int j = 0; j < cartGoods.size(); j++) {
            CartGoods goods = cartGoods.get(j);
            System.out.println("goodid"+goods.getGoodsId());
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setGoodsId(goods.getGoodsId());
            orderGoods.setGoodsName(goods.getGoodsName());
            orderGoods.setGoodsPrice(goods.getGoodsPrice());
            orderGoods.setGoodsNum(goods.getGoodsNum());
            orderGoods.setCreateBy(username);
            orderGoods.setOrderInfoId(Long.parseLong(orderInfoId));
            i2 = ordersMapper.insertOrderGoods(orderGoods);
            total += goods.getTotal();
            if (i2<=0){
                return 0;
            }
        }
        //订单列表中新增订单
        orderInfo.setOrderPrice(total);
        orderInfo.setCreateBy(username);
        int i = ordersMapper.insertOrder(orderInfo);
        if (i<=0){
            return 0;
        }
        //删除购物车中选中的商品
        int i1 = ordersMapper.deleteCartGoods(ids);
        if (i1<=0){
            return 0;
        }
        return i+i1+i2;
    }

    @Override
    public List<OrderInfo> findAllOrder(Integer userId) throws Exception {
        return ordersMapper.selectAllOrder(userId);
    }

    @Override
    public List<OrderInfo> selectWithWhere(int pageNum,int pageSize,String orderCode, String createTime,Integer userId, BaseResult result) throws Exception {
        List<OrderInfo> orderInfos = ordersMapper.selectWithWhere(orderCode, createTime, userId);
        result.setCount(orderInfos.size());
        PageHelper.startPage(pageNum, pageSize);
        return ordersMapper.selectWithWhere(orderCode,createTime,userId);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int deleteOrder(Integer id, Long orderCode) throws Exception {
        String s = Long.toString(orderCode);
        int i = ordersMapper.deleteOrderGoods(s);
        int i1 = ordersMapper.deleteOrder(id);
        return i+i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateCart(Double total, Integer id) throws Exception {
        return ordersMapper.updateCart(total, id);
    }

    @Override
    public OrderInfo findByOrderCode(String orderCode) throws Exception {
        return ordersMapper.selectByOrderCode(orderCode);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateOrder(String orderCode) throws Exception {
        return ordersMapper.updateOrder(orderCode);
    }
}
