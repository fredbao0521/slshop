package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer id;
    /**
    * 关联订单表
    */
    private Integer orderInfoId;
    /**
    * 会员类型
    */
    private String roleName;
    /**
    * 会员价格
    */
    private Double rolePrice;
    /**
    * 关联角色表
    */
    private Integer roleId;
    
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Double getRolePrice() {
        return rolePrice;
    }

    public void setRolePrice(Double rolePrice) {
        this.rolePrice = rolePrice;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}