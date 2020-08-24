package com.sy.bmq.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "AUUSER")
public class User implements Serializable {
    private static final long serialVersionUID = -46685883633415653L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 登录账号
    */
    private String username;
    /**
    * 登录密码（初始密码为证件号码后六位）
    */
    private String password;
    /**
    * 二级密码（初始密码为证件号码后六位）
    */
    private String password2;
    /**
    * 用户真实姓名
    */
    private String realname;
    /**
    * 性别
    */
    private String sex;
    /**
    * 生日
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
    * 证件类型id,来源于数据字典表 data_dictionary 中
typeCode =’CARD_TYPE’的 valueId
    */
    private String cardtype;
    /**
    * 证件类型名称,来源于数据字典表 data_dictionary 中
typeCode =’CARD_TYPE’的 valueName
    */
    private String cardtypename;
    /**
    * 证件号码
    */
    private String idcard;
    /**
    * 收货国家
    */
    private String country;
    /**
    * 手机
    */
    private String mobile;
    /**
    * email
    */
    private String email;
    /**
    * 收获地址
    */
    private String useraddress;
    /**
    * 邮编
    */
    private String postcode;
    /**
    * 注册时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    /**
    * 推荐人id（默认为当前登录用户id）
    */
    private Integer referid;
    /**
    * 推荐人编码（默认为当前登录用户loginCode）
    */
    private String refercode;
    /**
    * 所属角色ID
    */
    private Integer roleid;
    /**
    * 所属角色名称
    */
    private String rolename;
    /**
    * 是否启用（1、启用2、未启用）
    */
    private Integer isstart;
    /**
    * 最新更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastupdatetime;
    /**
    * 最后登录时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastlogintime;
    /**
    * 开户卡号
    */
    private String bankaccount;
    /**
    * 开户行
    */
    private String bankname;
    /**
    * 开户人
    */
    private String accountholder;
    /**
    * 身份证照片存放路径
    */
    private String idcardpicpath;
    /**
    * 银行卡照片存放路径
    */
    private String bankpicpath;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardtypename() {
        return cardtypename;
    }

    public void setCardtypename(String cardtypename) {
        this.cardtypename = cardtypename;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getReferid() {
        return referid;
    }

    public void setReferid(Integer referid) {
        this.referid = referid;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAccountholder() {
        return accountholder;
    }

    public void setAccountholder(String accountholder) {
        this.accountholder = accountholder;
    }

    public String getIdcardpicpath() {
        return idcardpicpath;
    }

    public void setIdcardpicpath(String idcardpicpath) {
        this.idcardpicpath = idcardpicpath;
    }

    public String getBankpicpath() {
        return bankpicpath;
    }

    public void setBankpicpath(String bankpicpath) {
        this.bankpicpath = bankpicpath;
    }

}