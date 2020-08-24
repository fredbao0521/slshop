package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 把数据放在ES中(GoodsPack)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "GOODSPACK")
public class GoodsPack implements Serializable {
    private static final long serialVersionUID = -19351308094536775L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 套餐名称
    */
    private String goodspackname;
    /**
    * 套餐编码
    */
    private String goodspackcode;
    /**
    * 套餐类型ID
    */
    private Integer typeid;
    /**
    * 套餐类型NAME
    */
    private String typename;
    /**
    * 套餐总价（系统生成，保存时根据相关商品的优惠价*数量计算生成）
    */
    private Object totalprice;
    /**
    * 状态（1、上架2、下架）
    */
    private Integer state;
    /**
    * 备注说明
    */
    private String note;
    /**
    * 库存数量
    */
    private Integer num;
    /**
    * 创建人
    */
    private String createdby;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 最后更新时间
    */
    private Date lastupdatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodspackname() {
        return goodspackname;
    }

    public void setGoodspackname(String goodspackname) {
        this.goodspackname = goodspackname;
    }

    public String getGoodspackcode() {
        return goodspackcode;
    }

    public void setGoodspackcode(String goodspackcode) {
        this.goodspackcode = goodspackcode;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Object getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Object totalprice) {
        this.totalprice = totalprice;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
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