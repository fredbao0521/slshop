package com.sy.bmq.service;

import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.OrderInfo;
import com.sy.bmq.model.Shopcart;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface OrdersService {
    int addCartGood(CartGoods cartGoods) throws Exception;

    Shopcart findCart(String username) throws Exception;

    int creatCart(Shopcart shopcart) throws Exception;

    int updateCartGood(CartGoods cartGoods) throws Exception;

    int deleteCartGood(CartGoods cartGoods) throws Exception;

    int insertOrder(OrderInfo orderInfo,String ids,String orderInfoId,String username) throws Exception;

    List<OrderInfo> findAllOrder(Integer userId) throws Exception;

    List<OrderInfo> selectWithWhere(int pageNum,int pageSize,String orderCode, String createTime,Integer userId, BaseResult result) throws Exception;

    int deleteOrder(Integer id,Long orderCode) throws Exception;
}
