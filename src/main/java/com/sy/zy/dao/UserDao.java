package com.sy.zy.dao;

import com.sy.bmq.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Insert("insert into au_user (username,password,realName,cardTypeName,idCard,country,mobile,email,userAddress,postCode,referCode,accountHolder" +
            ",bankAccount,idCardPicPath,bankName,bankPicPath,roleId,password2,createTime,roleName,isStart,lastLoginTime,lastUpdateTime)" +
            "values(#{username},#{password},#{realName},#{cardTypeName},#{idCard},#{country},#{mobile}," +
            "#{email},#{userAddress},#{postCode},#{referCode},#{accountHolder},#{bankAccount},#{idCardPicPath},#{bankName}," +
            "#{bankPicPath},#{roleId},#{password2},#{createTime},#{roleName},#{isStart},#{lastLoginTime},#{lastUpdateTime})")
    Integer add(User user)throws Exception;

/*查询会员*/
    @Select("<script> " +
            "SELECT * " +
            "FROM au_user " +
            " <where> " +
            " <if test=\"username != '' and username!=null\"> username= #{username}</if> " +
            " <if test=\"roleId >0\"> and roleId= #{roleId}</if> " +
            " <if test=\" id>0\"> and id = #{id}</if> " +
            " <if test=\" cardTypeName!='' and cardTypeName!=null\"> and cardTypeName=#{cardTypeName}</if> " +
            " </where> " +
            " </script> ")
    List<User> find(User user)throws Exception;


    /*修改密码*/
    @Update("update au_user set password=#{password} ,password2=#{password2} where username=#{username}")
    Integer modify(User user)throws Exception;

    /*修改个人资料*/
    @Update(
    "<script> " +
            "update" +
            " au_user " +
            " set realName=#{realName},cardTypeName=#{cardTypeName},idCard=#{idCard},country=#{country}, " +
            "            mobile=#{mobile},email=#{email},userAddress=#{userAddress},postCode=#{postCode}," +
            "            bankAccount=#{bankAccount},bankName=#{bankName},accountHolder=#{accountHolder}" +
            " <where> " +
            " <if test=\"username != '' and username!=null\"> username=#{username}</if> " +
            " <if test=' id>0'> and id=#{id}</if> " +
            " </where> " +
            " </script> ")
    Integer modifyMess(User user)throws Exception;

    /*根据id删除*/
    @Delete("delete from au_user where id=#{id}")
    Integer remove(int id)throws Exception;



    @Select("<script> " +
            "SELECT * " +
            "FROM au_user where roleId &gt;2" +
            " <if test=\"username != '' and username!=null\"> and username= #{username}</if> " +
            " </script> ")
    List<User> select(User user)throws Exception;



    @Select("SELECT count(*) from au_user where roleName=#{roleName}and roleId>1 ")
    Integer selectCount(String roleName)throws Exception;


}
