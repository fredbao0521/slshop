package com.sy.bmq.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 数据字典(DataDictionary)实体类
 *
 * @author makejava
 * @since 2020-08-24 12:26:26
 */
@Entity
@Table(name = "DATADICTIONARY")
public class DataDictionary implements Serializable {
    private static final long serialVersionUID = 450471301350607449L;
    /**
    * 主键ID
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
    * 类型编码
    */
    private String typeCode;
    /**
    * 类型名称
    */
    private String typeName;
    /**
    * 类型值ID
    */
    private Integer valueId;
    /**
    * 类型值Name
    */
    private String valueName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }
}