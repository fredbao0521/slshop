package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 购买商品订单明细表(OrderGoods)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "ORDERGOODS")
public class OrderGoods implements Serializable {
    private static final long serialVersionUID = -71560791537155611L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 商品编号,关联商品表
    */
    private Integer goodsId;
    /**
    * 商品名字
    */
    private String goodsName;
    /**
    * 商品价格
    */
    private Double goodsPrice;
    /**
    * 商品数量
    */
    private Integer goodsNum;
    /**
     * 创建人
     */
    private String createBy;
    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
    * 修改时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateTime;
    /**
    * 关联订单表
    */
    private Long orderInfoId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsNum=" + goodsNum +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", orderInfoId=" + orderInfoId +
                '}';
    }
}