package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 金钱账户表(UserAccount)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 827569624793294213L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 关联用户表
    */
    private Integer userId;
    /**
    * 账户余额
    */
    private Double balance;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;
    
    private String createBy;
    /**
    * 0锁定,1锁定
    */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", createBy='" + createBy + '\'' +
                ", status=" + status +
                '}';
    }
}