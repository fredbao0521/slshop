package com.sy.bmq.service.impl;

import com.sy.bmq.mapper.AccountDetailMapper;
import com.sy.bmq.mapper.UserAccountMapper;
import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.UserAccount;
import com.sy.bmq.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private AccountDetailMapper accountDetailMapper;

    @Override
    public UserAccount selectByUid(Integer id) throws Exception {
        return userAccountMapper.selectByUid(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addMoney(UserAccount userAccount,AccountDetail accountDetail) throws Exception {
        int i = userAccountMapper.addMoney(userAccount);
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        return i+i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int cashOut(UserAccount userAccount,Double balance,String otherAcountId) throws Exception {
        double v = userAccount.getBalance() - balance;
        userAccount.setBalance(v);
        int i = userAccountMapper.cashOut(userAccount);

        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setMoneyOut(balance);
        accountDetail.setMoneyIn(0.0);
        accountDetail.setType(1);
        accountDetail.setBalance(v);
        accountDetail.setOtherAcountId(otherAcountId);
        accountDetail.setAccountId(userAccount.getId());
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        return i+i1;
    }
}
