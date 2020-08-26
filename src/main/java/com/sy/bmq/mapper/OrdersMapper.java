package com.sy.bmq.mapper;

import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.Shopcart;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface OrdersMapper extends Mapper<CartGoods> {
    @Select("select shopcart.* from shopcart where shopcart.userId = (select au_user.id from au_user where au_user.username = #{username})")
    Shopcart findCart(String username) throws Exception;

    @Insert("INSERT INTO shopcart(userId,totalPrice,createby,lastUpdateTime,createTime)" +
            "values(#{userId},#{totalPrice},#{createby},now(),now())")
    int creatCart(Shopcart shopcart) throws Exception;

    @Insert("INSERT INTO cart_goods(goodsId,goodsName,goodsPrice,goodsNum,cartId,flag,createBy,createTime,lastUpdateTime) " +
            "VALUES(#{goodsId},#{goodsName},#{goodsPrice},#{goodsNum},#{cartId},#{flag},#{createBy},now(),now())")
    int addCartGood(CartGoods cartGoods) throws Exception;

    @Update("update cart_goods set goodsId=#{goodsId}," +
                                   "goodsName=#{goodsName}," +
                                   "goodsPrice=#{goodsPrice}," +
                                   "goodsNum=#{goodsNum}," +
                                   "flag=#{flag}," +
                                   "lastUpdateTime=now()" +
            "where id = #{id}")
    int updateCartGood(CartGoods cartGoods) throws Exception;

}
