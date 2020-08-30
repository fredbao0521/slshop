package com.sy.zy.dao;

import com.sy.bmq.model.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RoleDao {
  @Select("select * from au_role ")
    List<Role>  find()throws Exception;


  /*根据id删除*/
  @Delete("delete from au_role where id=#{id}")
  Integer remove(int id)throws Exception;

  @Update("update au_role set roleCode=#{roleCode},roleName=#{roleName},isStart=#{isStart} where id=#{id}")
  Integer modify(Role role)throws Exception;



  @Insert("insert into au_role (isStart,roleCode,roleName,createDate,createBy) values (#{isStart},#{roleCode},#{roleName},#{createDate},#{createBy})")
  Integer add(Role role)throws Exception;




}
