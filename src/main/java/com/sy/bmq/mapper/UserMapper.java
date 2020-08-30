package com.sy.bmq.mapper;

import com.sy.bmq.model.Role;
import com.sy.bmq.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from au_user where username = #{username}")
    User selectByUsername(String username) throws Exception;

    @Select("select * from au_role where roleName = #{roleName}")
    Role selectRoleName(String roleName) throws Exception;

    @Update("update au_user set roleId=#{roleId},roleName=#{roleName},lastUpdateTime = now() where id = #{id}")
    int updateUser(User user) throws Exception;

    @Select("select * from au_user where referCode = #{referCode}")
    User selectByReferCode(String referCode) throws Exception;
}
