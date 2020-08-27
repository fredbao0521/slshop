package com.sy.bmq.controller;


import com.sy.bmq.model.*;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
            Shopcart shopcart = ordersService.findCart(remoteUser);
            double total = cartGoods.getGoodsNum() * cartGoods.getGoodsPrice();
            cartGoods.setTotal(total);
            if (shopcart == null) {
                shopcart.setCreateBy(remoteUser);
                //先用固定金额填充
                shopcart.setTotalPrice(0.00);
                shopcart.setUserId(user.getId());
            } else {
                cartGoods.setCartId(shopcart.getId());
            }
            int i = ordersService.addCartGood(cartGoods);
            if (i >= 2) {
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("加入购物车成功");
            } else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("加入购物车失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/findcargoods.do")
    public BaseResult FindCartGoods(BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();

        try {
            User user = userService.selectByUsername(remoteUser);
            List<CartGoods> cartGoods = goodsService.selectByCartId(user.getId());
            result.setData(cartGoods);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/delcargoods.do")
    public BaseResult DelCartGoods(CartGoods cartGoods,BaseResult result) {
        try {
            int i = ordersService.deleteCartGood(cartGoods);
            if (i>=2){
                result.setData(cartGoods);
                result.setCode(BaseResult.CODE_SUCCESS);
            }else {
                result.setCode(BaseResult.CODE_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/createorder.do")
    public BaseResult createOrder(Integer[] ids,BaseResult result,HttpServletRequest request) {
        //生成订单号
        String orderNumber = generateOrderNumber();
        System.out.println("orderNumber====="+orderNumber);
        //选择的购物车中的商品id列表
        String id = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i!=ids.length-1){
                id =id+ids[i]+",";
            }else {
                id = id + ids[i]+")";
            }
        }
        System.out.println("id====="+id);
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            System.out.println("user====="+user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderCode(orderNumber);
        orderInfo.setUserId(user.getId());
        System.out.println("orderInfo====="+orderInfo.toString());
        try {
            int i = ordersService.insertOrder(orderInfo,id,orderNumber,user.getUsername());
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/findallorders.do")
    public BaseResult FindAllOrders(int limit,int page,String orderCode,String createTime,BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        try {
            User user = userService.selectByUsername(remoteUser);
            List<OrderInfo> allOrder = ordersService.selectWithWhere(page,limit,orderCode,createTime,user.getId(),result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(allOrder);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }







    public static String generateOrderNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(cal.getTime());
        return format + getRandomNum(5);
    }
        /**
         * 获取随机字符串
         *
         * @param num
         * @return
         */
        public static String getRandomNum(Integer num) {
            String base = "0123456789";
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < num; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            return sb.toString();
        }


}
