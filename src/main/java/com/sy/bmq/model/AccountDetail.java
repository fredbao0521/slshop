package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer id;
    /**
    * 关联用户账户表
    */
    private Integer accountId;
    /**
    * 记录入账或出账日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date accountDate;
    /**
    * 入账
    */
    private Double moneyIn;
    /**
    * 出账
    */
    private Double moneyOut;
    /**
    * 0充值,1提现,2消费,3收益
    */
    private Integer type;
    /**
    * 交易方账户
    */
    private String otherAcountId;

    private Double balance;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getAccountDate() {
        return accountDate;
    }

    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
    }

    public Double getMoneyIn() {
        return moneyIn;
    }

    public void setMoneyIn(Double moneyIn) {
        this.moneyIn = moneyIn;
    }

    public Double getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(Double moneyOut) {
        this.moneyOut = moneyOut;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOtherAcountId() {
        return otherAcountId;
    }

    public void setOtherAcountId(String otherAcountId) {
        this.otherAcountId = otherAcountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", accountDate=" + accountDate +
                ", moneyIn=" + moneyIn +
                ", moneyOut=" + moneyOut +
                ", type=" + type +
                ", otherAcountId='" + otherAcountId + '\'' +
                ", balance=" + balance +
                ", state=" + state +
                '}';
    }
}