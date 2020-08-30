package com.sy.bmq.mapper;

import com.sy.bmq.model.AccountDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AccountDetailMapper extends Mapper<AccountDetail> {

    @Insert("insert into account_detail(accountId,accountDate,moneyIn,moneyOut,type,otherAcountId,balance,state) values(#{accountId},now(),#{moneyIn},#{moneyOut},#{type},#{otherAcountId},#{balance},#{state})")
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
            "<if test=\'type != null and type != \"\"\'>" +
            " and type = 1" +
            "</if>" +
            "</where></script>"
    })
    List<AccountDetail> selectWithWhere2(@Param("beginTime") String beginTime,
                                        @Param("endTime") String endTime,
                                        @Param("accountId") Integer accountId,
                                         @Param("type") Integer  type) throws Exception;

    @Select({"<script>" +
            "SELECT " +
            " * " +
            " from account_detail" +
            "<where>" +
            "<if test=\'beginTime != null and beginTime != \"\" and endTime != null and endTime != \"\"\'>" +
            "and accountDate between  #{beginTime} and  #{endTime}" +
            "</if>" +
            "<if test=\'type != null and type != \"\"\'>" +
            " and type = 1" +
            "</if>" +
            "</where></script>"
    })
    List<AccountDetail> selectWithWhere3(@Param("beginTime") String beginTime,
                                         @Param("endTime") String endTime,
                                         @Param("type") Integer  type) throws Exception;


    @Update("update account_detail set state = #{state} where id = #{id}")
    int pass(Integer id, Integer state) throws Exception;

    @Select("select * from account_detail where accountId = #{id} and type = 2")
    List<AccountDetail> findPay(Integer id) throws Exception;

}
