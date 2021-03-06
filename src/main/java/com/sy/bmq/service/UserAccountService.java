package com.sy.bmq.service;


import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.UserAccount;

public interface UserAccountService {

    UserAccount selectByUid(Integer id) throws Exception;

    int addMoney(UserAccount userAccount, AccountDetail accountDetail) throws Exception;

    int cashOut(UserAccount userAccount, Double balance, String otherAcountId) throws Exception;

    int modifyOut(UserAccount userAccount, Double balance, Integer toId) throws Exception;

    int payOrder(Double pay,UserAccount userAccount) throws Exception;

    int byVip(String username,String roleName,Double balance,String password2) throws Exception;
}
