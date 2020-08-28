package com.sy.bmq.controller;


import com.sy.bmq.model.*;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.*;
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
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 商品添加购物车
     *
     * @param cartGoods
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/addcargoods.do")
    public BaseResult addCartGoods(CartGoods cartGoods, BaseResult result, HttpServletRequest request) {
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
            if (cartGoods.getGoodsNum()==null){
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("库存不足,加入购物车失败");
                return result;
            }
            double total = cartGoods.getGoodsNum() * cartGoods.getGoodsPrice();
            cartGoods.setTotal(total);
            if (shopcart == null) {
                shopcart = new Shopcart();
                shopcart.setCreateBy(remoteUser);
                //先用固定金额填充
                shopcart.setTotalPrice(0.00);
                shopcart.setUserId(user.getId());
                ordersService.creatCart(shopcart);

            }
            shopcart = ordersService.findCart(remoteUser);
            cartGoods.setCartId(shopcart.getId());
            int i = ordersService.addCartGood(cartGoods);
            List<CartGoods> cartGoods1 = goodsService.selectByCartId(shopcart.getId());
            Double cattotal = 0.0;
            for (int j = 0; j < cartGoods1.size(); j++) {
                cattotal += cartGoods1.get(j).getTotal();
            }
            ordersService.updateCart(cattotal, shopcart.getId());
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

    /**
     * 查询当前用户购物车中的商品
     *
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/findcargoods.do")
    public BaseResult findCartGoods(BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();

        try {
//            User user = userService.selectByUsername(remoteUser);
            Shopcart cart = ordersService.findCart(remoteUser);
            List<CartGoods> cartGoods = goodsService.selectByCartId(cart.getId());
            result.setData(cartGoods);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除当前用户购物车中的商品
     *
     * @param cartGoods
     * @param result
     * @return
     */
    @RequestMapping("/delcargoods.do")
    public BaseResult delCartGoods(CartGoods cartGoods, BaseResult result) {
        try {
            int i = ordersService.deleteCartGood(cartGoods);
            if (i >= 2) {
                result.setData(cartGoods);
                result.setCode(BaseResult.CODE_SUCCESS);
            } else {
                result.setCode(BaseResult.CODE_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 将购物车中的商品加入到订单中,并移除购物车中选定的商品
     *
     * @param ids
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/createorder.do")
    public BaseResult createOrder(Integer[] ids, BaseResult result, HttpServletRequest request) {
        //生成订单号
        String orderNumber = generateOrderNumber();
        System.out.println("orderNumber=====" + orderNumber);
        //选择的购物车中的商品id列表
        String id = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i != ids.length - 1) {
                id = id + ids[i] + ",";
            } else {
                id = id + ids[i] + ")";
            }
        }
        System.out.println("id=====" + id);
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            System.out.println("user=====" + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderCode(orderNumber);
        orderInfo.setUserId(user.getId());
        System.out.println("orderInfo=====" + orderInfo.toString());
        try {
            int i = ordersService.insertOrder(orderInfo, id, orderNumber, user.getUsername());
            OrderInfo byOrderCode = ordersService.findByOrderCode(orderNumber);
            result.setData(byOrderCode);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询当前用户的所有订单
     *
     * @param limit
     * @param page
     * @param orderCode
     * @param createTime
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/findallorders.do")
    public BaseResult findAllOrders(int limit, int page, String orderCode, String createTime, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        try {
            User user = userService.selectByUsername(remoteUser);
            List<OrderInfo> allOrder = ordersService.selectWithWhere(page, limit, orderCode, createTime, user.getId(), result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(allOrder);
            result.setCode(BaseResult.CODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除订单,并移除订单中的商品
     *
     * @param id
     * @param orderCode
     * @param result
     * @return
     */
    @RequestMapping("/delorder.do")
    public BaseResult delOrder(Integer id, String orderCode, BaseResult result) {
        try {
            long l = Long.parseLong(orderCode);
            int i = ordersService.deleteOrder(id, l);
            if (i > 0) {
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("删除成功");
            } else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @RequestMapping("/payorder.do")
    public BaseResult payOrder(String orderCode,String password,BaseResult result,HttpServletRequest request) {

        System.out.println(orderCode);
        System.out.println(password);
        try {
            //获取到订单信息
            OrderInfo orderInfo = ordersService.findByOrderCode(orderCode);
            //获取用户余额
            String remoteUser = request.getRemoteUser();
            User user = userService.selectByUsername(remoteUser);
            if (!password.equals(user.getPassword2())){
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("支付失败");
                return result;
            }
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            double v = userAccount.getBalance() - orderInfo.getOrderPrice();
            if (v>=0){
                userAccount.setBalance(v);
                int i = userAccountService.payOrder(orderInfo.getOrderPrice(), userAccount);
                ordersService.updateOrder(orderCode);
                if (i>0){
                    result.setCode(BaseResult.CODE_SUCCESS);
                    result.setMsg("支付成功");
                }else {
                    result.setCode(BaseResult.CODE_FAILED);
                    result.setMsg("支付失败");
                }
            }else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("余额不足,支付失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取当前时间,与随机字符串组合成一个不会重复的订单号
     *
     * @return
     */
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
