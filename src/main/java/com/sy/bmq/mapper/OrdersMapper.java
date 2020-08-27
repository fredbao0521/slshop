package com.sy.bmq.mapper;

import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.OrderGoods;
import com.sy.bmq.model.OrderInfo;
import com.sy.bmq.model.Shopcart;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrdersMapper extends Mapper<CartGoods> {
    @Select("select shopcart.* from shopcart where shopcart.userId = (select au_user.id from au_user where au_user.username = #{username})")
    Shopcart findCart(String username) throws Exception;

    @Insert("INSERT INTO shopcart(userId,totalPrice,createby,lastUpdateTime,createTime)" +
            "values(#{userId},#{totalPrice},#{createby},now(),now())")
    int creatCart(Shopcart shopcart) throws Exception;

    @Insert("INSERT INTO cart_goods(goodsId,goodsName,goodsPrice,goodsNum,cartId,flag,createBy,createTime,lastUpdateTime,total) " +
            "VALUES(#{goodsId},#{goodsName},#{goodsPrice},#{goodsNum},#{cartId},#{flag},#{createBy},now(),now(),#{total})")
    int addCartGood(CartGoods cartGoods) throws Exception;

    @Update("update cart_goods set goodsId=#{goodsId}," +
                                   "goodsName=#{goodsName}," +
                                   "goodsPrice=#{goodsPrice}," +
                                   "goodsNum=#{goodsNum}," +
                                   "flag=#{flag}," +
                                   "lastUpdateTime=now()" +
                                   "total=#{total}" +
            "where id = #{id}")
    int updateCartGood(CartGoods cartGoods) throws Exception;

    @Delete("delete from cart_goods where id = #{id}")
    int deleteCartGood(CartGoods cartGoods) throws Exception;

    @Delete("delete from cart_goods where id in ${ids}")
    int deleteCartGoods(@Param("ids") String ids) throws Exception;

    @Select("select * from cart_goods where id in ${ids}")
    List<CartGoods> findCartGoods(@Param("ids") String ids) throws Exception;
    /**
     * 以下是order订单的数据操作
     */

    @Insert("insert into order_info(orderCode,orderPrice,createTime,createBy,lastUpdateTime,status,userId)" +
            "values(#{orderCode},#{orderPrice},now(),#{createBy},now(),2,#{userId})")
    int insertOrder(OrderInfo orderInfo) throws Exception;

    @Insert("insert into order_goods(goodsId,goodsName,goodsPrice,goodsNum,createBy,createTime,lastUpdateTime,orderInfoId)" +
            "values(#{goodsId},#{goodsName},#{goodsPrice},#{goodsNum},#{createBy},now(),now(),#{orderInfoId})")
    int insertOrderGoods(OrderGoods orderGoods) throws Exception;
}
