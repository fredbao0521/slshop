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
    private Long id;
    /**
    * 商品编码
    */
    private String goodssn;
    /**
    * 商品名称
    */
    private String goodsname;
    /**
    * 商品规格
    */
    private String goodsformat;
    /**
    * 市场价
    */
    private Object marketprice;
    /**
    * 优惠价格
    */
    private Object realprice;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodssn() {
        return goodssn;
    }

    public void setGoodssn(String goodssn) {
        this.goodssn = goodssn;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsformat() {
        return goodsformat;
    }

    public void setGoodsformat(String goodsformat) {
        this.goodsformat = goodsformat;
    }

    public Object getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Object marketprice) {
        this.marketprice = marketprice;
    }

    public Object getRealprice() {
        return realprice;
    }

    public void setRealprice(Object realprice) {
        this.realprice = realprice;
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

}