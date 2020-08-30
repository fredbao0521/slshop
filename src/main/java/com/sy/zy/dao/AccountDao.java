package com.sy.zy.dao;

import com.sy.bmq.model.AccountDetail;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {
    @Select("select * from account_detail where type=3")
    List<AccountDetail> select()throws Exception;
}
