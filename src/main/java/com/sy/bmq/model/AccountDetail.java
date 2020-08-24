package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 账户资金明细表(AccountDetail)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:21
 */
@Entity
@Table(name = "ACCOUNT_DETAIL")
public class AccountDetail implements Serializable {
    private static final long serialVersionUID = 525595526611964394L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 关联用户账户表
    */
    private Long accountid;
    /**
    * 记录入账或出账日期
    */
    private Date accountdate;
    /**
    * 入账
    */
    private Double moneyin;
    /**
    * 出账
    */
    private Double moneyout;
    /**
    * 0充值,1提现,2消费,3收益
    */
    private Integer type;
    /**
    * 交易方账户
    */
    private String otheracountid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public Date getAccountdate() {
        return accountdate;
    }

    public void setAccountdate(Date accountdate) {
        this.accountdate = accountdate;
    }

    public Double getMoneyin() {
        return moneyin;
    }

    public void setMoneyin(Double moneyin) {
        this.moneyin = moneyin;
    }

    public Double getMoneyout() {
        return moneyout;
    }

    public void setMoneyout(Double moneyout) {
        this.moneyout = moneyout;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOtheracountid() {
        return otheracountid;
    }

    public void setOtheracountid(String otheracountid) {
        this.otheracountid = otheracountid;
    }

}