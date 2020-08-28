package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表(Role)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "AUROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = -94509230782670901L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 角色编码
    */
    private String roleCode;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 创建日期
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;
    /**
    * 是否启用（0、未启用1、启用）
    */
    private Integer isStart;
    /**
    * 创建者
    */
    private String createBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsStart() {
        return isStart;
    }

    public void setIsStart(Integer isStart) {
        this.isStart = isStart;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}