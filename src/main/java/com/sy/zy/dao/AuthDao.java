package com.sy.zy.dao;

import com.sy.bmq.model.AuAuthority;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuthDao  {
    @Select("select * from au_authority where roleId=#{roleId}")
    List<AuAuthority> find(int id)throws Exception;

    @Select("select * from au_authority where functionId=#{functionId}")
    AuAuthority find1(int id)throws Exception;

    @Delete("delete from au_authority where functionId=#{functionId}")
    Integer remove(int parId)throws Exception;

}
