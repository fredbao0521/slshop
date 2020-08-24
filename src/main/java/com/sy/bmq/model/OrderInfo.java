package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(OrderInfo)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "ORDERINFO")
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 421430721010606329L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 订单编号
    */
    private String ordercode;
    /**
    * 订单总价
    */
    private Object orderprice;
    /**
    * 订单时间
    */
    private Date createtime;
    /**
    * 创建者
    */
    private String createby;
    /**
    * 修改时间
    */
    private Date lastupdatetime;
    /**
    * 1已支付,0失效订单,2未支付
    */
    private Integer status;
    /**
    * 关联用户表
    */
    private Long userid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public Object getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Object orderprice) {
        this.orderprice = orderprice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

}