package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 角色权限关联表(AuAuthority)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "AUAUTHORITY")
public class AuAuthority implements Serializable {
    private static final long serialVersionUID = 954999781806881238L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
    * 角色ID
    */
    private Long roleid;
    /**
    * 功能ID
    */
    private Long functionid;
    /**
    * 创建时间
    */
    private Date creationtime;
    /**
    * 创建者
    */
    private String createdby;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getFunctionid() {
        return functionid;
    }

    public void setFunctionid(Long functionid) {
        this.functionid = functionid;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

}