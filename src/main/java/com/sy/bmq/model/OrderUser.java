package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 购买会员订单明细表(OrderUser)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "ORDERUSER")
public class OrderUser implements Serializable {
    private static final long serialVersionUID = -70565669146538374L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 关联订单表
    */
    private Long orderinfoid;
    /**
    * 会员类型
    */
    private String rolename;
    /**
    * 会员价格
    */
    private Object roleprice;
    /**
    * 关联角色表
    */
    private Long roleid;
    
    private String createby;
    
    private Date createdatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderinfoid() {
        return orderinfoid;
    }

    public void setOrderinfoid(Long orderinfoid) {
        this.orderinfoid = orderinfoid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Object getRoleprice() {
        return roleprice;
    }

    public void setRoleprice(Object roleprice) {
        this.roleprice = roleprice;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

}