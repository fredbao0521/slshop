package com.sy.bmq.mapper;

import com.sy.bmq.model.AccountDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AccountDetailMapper extends Mapper<AccountDetail> {

    @Insert("insert into account_detail(accountId,accountDate,moneyIn,moneyOut,type,otherAcountId,balance) values(#{accountId},now(),#{moneyIn},#{moneyOut},#{type},#{otherAcountId},#{balance})")
    int addAccountDetail(AccountDetail accountDetail) throws Exception;

    @Select({"<script>" +
            "SELECT " +
            " * " +
            " from account_detail" +
            "<where>" +
            "<if test=\'beginTime != null and beginTime != \"\" and endTime != null and endTime != \"\"\'>" +
            "and accountDate between  #{beginTime} and  #{endTime}" +
            "</if>" +
            "<if test=\'accountId != null and accountId != \"\"\'>" +
            " and accountId = #{accountId}" +
            "</if>" +
            "</where></script>"
    })
    List<AccountDetail> selectWithWhere(@Param("beginTime") String beginTime,
                                        @Param("endTime") String endTime,
                                        @Param("accountId") Integer accountId) throws Exception;
}
