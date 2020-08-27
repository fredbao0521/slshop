package com.sy.bmq.controller;


import com.sy.bmq.model.AccountDetail;
import com.sy.bmq.model.User;
import com.sy.bmq.model.UserAccount;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.AccountDetailService;
import com.sy.bmq.service.UserAccountService;
import com.sy.bmq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountDetailService accountDetailService;


    @RequestMapping("/addmoney.do")
    public BaseResult addMoney(AccountDetail accountDetail, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        User user = null;
        int i = 0;
        try {
            user = userService.selectByUsername(remoteUser);
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            double balance = userAccount.getBalance() + accountDetail.getMoneyIn();
            userAccount.setBalance(balance);
            accountDetail.setAccountId(userAccount.getId());
            accountDetail.setMoneyOut(0.0);
            accountDetail.setBalance(balance);
            accountDetail.setType(0);
            accountDetail.setOtherAcountId(user.getBankAccount());
            i = userAccountService.addMoney(userAccount, accountDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i >= 2) {
            result.setCode(BaseResult.CODE_SUCCESS);
            result.setMsg("账户充值成功");
        } else {
            result.setCode(BaseResult.CODE_FAILED);
            result.setMsg("账户充值失败");
        }
        return result;
    }

    @RequestMapping("/list.do")
    public BaseResult listAccountDetail(int limit, int page, String beginTime, String endTime, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            List<AccountDetail> accountDetails = accountDetailService.selectWithWhere(page, limit, beginTime, endTime, userAccount.getId(), result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(accountDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_SUCCESS);
        return result;
    }

    @RequestMapping("/getbalance.do")
    public BaseResult getBalance(BaseResult result, HttpServletRequest request) {
        UserAccount userAccount = null;
        try {
            String remoteUser = request.getRemoteUser();
            User user = userService.selectByUsername(remoteUser);
            userAccount = userAccountService.selectByUid(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(userAccount);
        if (userAccount.getBalance() <= 0) {
            result.setCode(BaseResult.CODE_FAILED);
        } else {
            result.setCode(BaseResult.CODE_SUCCESS);
        }
        return result;
    }

    @RequestMapping("/cashout.do")
    public BaseResult cashOut(Double balance,String otherAcountId,BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        try {
            User user = userService.selectByUsername(remoteUser);
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            int i = userAccountService.cashOut(userAccount,balance,otherAcountId);
            if (i >= 2) {
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("提现成功");
            } else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("提现失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
