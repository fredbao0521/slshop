package com.sy.bmq.mapper;

import com.sy.bmq.model.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select * from au_user where username = #{username}")
    User selectByUsername(String username) throws Exception;
}
