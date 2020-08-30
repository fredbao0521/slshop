package com.sy.bmq.service;


import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface AccountDetailService {

    int addAccountDetail(AccountDetail accountDetail) throws Exception;

    List<AccountDetail> selectWithWhere(int pageNum, int pageSize, String beginTime, String endTime, Integer accountId, BaseResult result) throws Exception;

    List<AccountDetail> selectWithWhere2(int pageNum, int pageSize, String beginTime, String endTime, Integer accountId, BaseResult result) throws Exception;

    List<AccountDetail> selectWithWhere3(int pageNum, int pageSize, String beginTime, String endTime, BaseResult result) throws Exception;

    int pass(Integer id, Integer state) throws Exception;

    List<AccountDetail> findPay(Integer id) throws Exception;
}
