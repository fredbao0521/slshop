package com.sy.bmq.controller;


import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.GoodsInfo;
import com.sy.bmq.model.Shopcart;
import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.GoodsService;
import com.sy.bmq.service.OrdersService;
import com.sy.bmq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addcargoods.do")
    public BaseResult AddCartGoods(CartGoods cartGoods, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        cartGoods.setCreateBy(remoteUser);
        try {
            GoodsInfo goodsInfo = goodsService.selectById(cartGoods.getGoodsId());
            User user = userService.selectByUsername(remoteUser);
            if (goodsInfo.getNum() == 0) {
                cartGoods.setFlag(0);
            } else {
                cartGoods.setFlag(1);
            }
            Shopcart cart = ordersService.findCart(remoteUser);
            if (cart == null) {
                cart.setCreateBy(remoteUser);
                cart.setTotalPrice(1000.00);
                cart.setUserId(user.getId());
            } else {
                cartGoods.setCartId(cart.getId());
            }
            int i = ordersService.addCartGood(cartGoods);
            if (i>=2){
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("加入购物车成功");
            }else{
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("加入购物车失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
