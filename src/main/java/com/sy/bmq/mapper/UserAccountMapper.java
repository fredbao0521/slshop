package com.sy.bmq.mapper;

import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.UserAccount;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;


public interface UserAccountMapper extends Mapper<AccountDetail> {

    @Select("select * from user_account where userId = #{userId}")
    UserAccount selectByUid(Integer id) throws Exception;


    @Update("update user_account set balance = #{balance},lastUpdateTime = now() where userId = #{userId}")
    int addMoney(UserAccount userAccount) throws Exception;

    @Update("update user_account set balance = #{balance},lastUpdateTime = now() where userId = #{userId}")
    int cashOut(UserAccount userAccount) throws Exception;


}
