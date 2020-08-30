package com.sy.bmq.service.impl;

import com.sy.bmq.mapper.AccountDetailMapper;
import com.sy.bmq.mapper.UserAccountMapper;
import com.sy.bmq.mapper.UserMapper;
import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.Role;
import com.sy.bmq.model.User;
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
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserAccount selectByUid(Integer id) throws Exception {
        return userAccountMapper.selectByUid(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int addMoney(UserAccount userAccount, AccountDetail accountDetail) throws Exception {
        int i = userAccountMapper.addMoney(userAccount);
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        return i + i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int cashOut(UserAccount userAccount, Double balance, String otherAcountId) throws Exception {
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
        accountDetail.setState(0);
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        return i + i1;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int modifyOut(UserAccount userAccount, Double balance, Integer toId) throws Exception {
        //转出
        double v = userAccount.getBalance() - balance;
        if (v<0){
            return 0;
        }
        userAccount.setBalance(v);
        int i = userAccountMapper.cashOut(userAccount);
        //转入
        UserAccount toAccount = userAccountMapper.selectByUid(toId);
        double v1 = toAccount.getBalance() + balance;
        toAccount.setBalance(v1);
        int i1 = userAccountMapper.cashOut(toAccount);
        //写转账记录
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setMoneyOut(balance);
        accountDetail.setMoneyIn(0.0);
        accountDetail.setType(4);
        accountDetail.setBalance(v);
        accountDetail.setOtherAcountId("0");
        accountDetail.setState(1);
        accountDetail.setAccountId(userAccount.getId());
        int i2 = accountDetailMapper.addAccountDetail(accountDetail);
        return i + i1+i2;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int payOrder(Double pay,UserAccount userAccount) throws Exception {
        //扣款
        int i =userAccountMapper.cashOut(userAccount);

        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setMoneyOut(pay);
        accountDetail.setMoneyIn(0.0);
        accountDetail.setType(2);
        accountDetail.setBalance(userAccount.getBalance());
        accountDetail.setOtherAcountId("0");
        accountDetail.setAccountId(userAccount.getId());
        accountDetail.setState(1);
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        return i + i1;
    }

    /**
     * 购买会员
     * @param username 自己名字
     * @param balance 买会员费用
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int byVip(String username,String roleName,Double balance,String password2) throws Exception {
        //查询自己的信息
        User user = userMapper.selectByUsername(username);
        //查询自己的账户信息
        UserAccount userAccount = userAccountMapper.selectByUid(user.getId());
        //会员购买后的余额
        double v = userAccount.getBalance() - balance;
        userAccount.setBalance(v);
        //更新自己的账户余额表
        int i = userAccountMapper.cashOut(userAccount);
        //设置账户详情
        AccountDetail accountDetail = new AccountDetail();
        accountDetail.setMoneyOut(balance);
        accountDetail.setMoneyIn(0.0);
        accountDetail.setType(2);
        accountDetail.setBalance(v);
        accountDetail.setOtherAcountId("0");
        accountDetail.setAccountId(userAccount.getId());
        accountDetail.setState(1);
        //更新自己的账户详情表
        int i1 = accountDetailMapper.addAccountDetail(accountDetail);
        //查询会员名对应的信息
        Role role = userMapper.selectRoleName(roleName);
        //更新自己会员状态
        user.setRoleId(role.getId());
        user.setRoleName(role.getRoleName());
        int i2 = userMapper.updateUser(user);
        //查询上级代理商
        User referUser = userMapper.selectByUsername(user.getReferCode());
        //查询代理商的账户信息
        UserAccount referAccount = userAccountMapper.selectByUid(referUser.getId());
        //代理得到分成收益余额
        Double money = 0.0;
        if (roleName.equals("黄金会员")){
            money = balance*0.1;
        }else if (roleName.equals("铂金会员")){
            money = balance*0.2;
        }else if (roleName.equals("钻石会员")){
            money = balance*0.3;
        }
        double v1 = referAccount.getBalance() + money;
        referAccount.setBalance(v1);
        //更新自己的账户余额表
        int i3 = userAccountMapper.cashOut(referAccount);
        //设置代理商账户详情
        AccountDetail accountDetail2 = new AccountDetail();
        accountDetail2.setMoneyOut(0.0);
        accountDetail2.setMoneyIn(money);
        accountDetail2.setType(3);
        accountDetail2.setBalance(v1);
        accountDetail2.setOtherAcountId("0");
        accountDetail2.setAccountId(referAccount.getId());
        accountDetail2.setState(1);
        //更新自己的账户详情表
        int i4 = accountDetailMapper.addAccountDetail(accountDetail2);
        return i+i1+i2+i3+i4;
    }
}
