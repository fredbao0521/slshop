package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.sy.bmq.mapper.AccountDetailMapper;
import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    private AccountDetailMapper accountDetailMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addAccountDetail(AccountDetail accountDetail) throws Exception {
        return accountDetailMapper.addAccountDetail(accountDetail);
    }

    @Override
    public List<AccountDetail> selectWithWhere(int pageNum, int pageSize, String beginTime, String endTime, Integer accountId, BaseResult result) throws Exception {
        List<AccountDetail> accountDetails = accountDetailMapper.selectWithWhere(beginTime, endTime, accountId);
        result.setCount(accountDetails.size());
        PageHelper.startPage(pageNum, pageSize);
        return accountDetailMapper.selectWithWhere(beginTime, endTime, accountId);
    }

    @Override
    public List<AccountDetail> selectWithWhere2(int pageNum, int pageSize, String beginTime, String endTime, Integer accountId, BaseResult result) throws Exception {
        List<AccountDetail> accountDetails = accountDetailMapper.selectWithWhere2(beginTime, endTime, accountId,1);
        result.setCount(accountDetails.size());
        PageHelper.startPage(pageNum, pageSize);
        return accountDetailMapper.selectWithWhere2(beginTime, endTime, accountId,1);
    }

    @Override
    public List<AccountDetail> selectWithWhere3(int pageNum, int pageSize, String beginTime, String endTime,BaseResult result) throws Exception {
        List<AccountDetail> accountDetails = accountDetailMapper.selectWithWhere3(beginTime, endTime,1);
        result.setCount(accountDetails.size());
        PageHelper.startPage(pageNum, pageSize);
        return accountDetailMapper.selectWithWhere3(beginTime, endTime,1);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int pass(Integer id, Integer state) throws Exception {
        return accountDetailMapper.pass(id,state);
    }

    @Override
    public List<AccountDetail> findPay(Integer id) throws Exception {
        return accountDetailMapper.findPay(id);
    }
}
