package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 购物车中商品信息表(CartGoods)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "CARTGOODS")
public class CartGoods implements Serializable {
    private static final long serialVersionUID = -99843413252142272L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 商品编号,关联商品表
    */
    private Long goodsid;
    /**
    * 商品名字
    */
    private String goodsname;
    /**
    * 商品价格
    */
    private Object goodsprice;
    /**
    * 商品数量
    */
    private Integer goodsnum;
    /**
    * 购物车ID,关联购物车表
    */
    private Long cartid;
    /**
    * 0表示无货,1表示有货
    */
    private Integer flag;
    /**
    * 创建人
    */
    private String createby;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 修改时间
    */
    private Date lastupdatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Long goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Object getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Object goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(Integer goodsnum) {
        this.goodsnum = goodsnum;
    }

    public Long getCartid() {
        return cartid;
    }

    public void setCartid(Long cartid) {
        this.cartid = cartid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

}