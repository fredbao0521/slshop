package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 把该表数据放ES中(GoodsInfo)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "GOODSINFO")
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -93894276421397687L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 商品编码
    */
    private String goodsSN;
    /**
    * 商品名称
    */
    private String goodsName;
    /**
    * 商品规格
    */
    private String goodsFormat;
    /**
    * 市场价
    */
    private Double marketPrice;
    /**
    * 优惠价格
    */
    private Double realPrice;
    /**
    * 状态（1、上架、2、下架）
    */
    private Integer state;
    /**
    * 商品说明
    */
    private String note;
    /**
    * 库存数量
    */
    private Integer num;
    /**
    * 单位
    */
    private String unit;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 最后更新时间
    */
    private Date lastupdatetime;
    /**
    * 创建人
    */
    private String createdby;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsSN() {
        return goodsSN;
    }

    public void setGoodsSN(String goodsSN) {
        this.goodsSN = goodsSN;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFormat() {
        return goodsFormat;
    }

    public void setGoodsFormat(String goodsFormat) {
        this.goodsFormat = goodsFormat;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", goodsSn='" + goodsSN + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsFormat='" + goodsFormat + '\'' +
                ", marketPrice=" + marketPrice +
                ", realPrice=" + realPrice +
                ", state=" + state +
                ", note='" + note + '\'' +
                ", num=" + num +
                ", unit='" + unit + '\'' +
                ", createtime=" + createtime +
                ", lastupdatetime=" + lastupdatetime +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}