package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 销售额报表(Report2)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "REPORT2")
public class Report2 implements Serializable {
    private static final long serialVersionUID = 660827316151936061L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 销售额度
    */
    private Double totalMoney;
    
    private Date createTime;
    /**
    * 0会员销售,1商品销售
    */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}