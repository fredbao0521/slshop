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


    /**
     * 个人账户充值汇款
     * @param accountDetail
     * @param result
     * @param request
     * @return
     */
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
            accountDetail.setState(1);
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

    /**
     * 显示个人账户交易信息
     * @param limit
     * @param page
     * @param beginTime
     * @param endTime
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    public BaseResult listAccountDetail(int limit, int page, String beginTime, String endTime, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            System.out.println("user====="+user.toString());
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

    /**
     * 显示个人账户提现信息
     * @param limit
     * @param page
     * @param beginTime
     * @param endTime
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/list3.do")
    public BaseResult listAccountDetail2(int limit, int page, String beginTime, String endTime, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            System.out.println("user====="+user.toString());
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            List<AccountDetail> accountDetails = accountDetailService.selectWithWhere2(page, limit, beginTime, endTime, userAccount.getId(), result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(accountDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_SUCCESS);
        return result;
    }

    /**
     * 显示个人账户交易信息
     * @param limit
     * @param page
     * @param beginTime
     * @param endTime
     * @param result
     * @return
     */
    @RequestMapping("/list2.do")
    public BaseResult listAccountDetail2(int limit, int page, String beginTime, String endTime, BaseResult result) {
        try {

            List<AccountDetail> accountDetails = accountDetailService.selectWithWhere3(page, limit, beginTime, endTime, result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(accountDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_SUCCESS);
        return result;
    }

    /**
     * 获取当前账户余额
     * @param result
     * @param request
     * @return
     */
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

    /**
     * 提现
     * @param balance
     * @param otherAcountId
     * @param result
     * @param request
     * @return
     */
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

    /**
     * \当前账户提现操作记录
     * @param limit
     * @param page
     * @param beginTime
     * @param endTime
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/cashoutlist.do")
    public BaseResult cashOutList(int limit, int page, String beginTime, String endTime, BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        User user = null;
        try {
            user = userService.selectByUsername(remoteUser);
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            List<AccountDetail> accountDetails = accountDetailService.selectWithWhere2(page, limit, beginTime, endTime, userAccount.getId(), result);
            result.setPage(page);
            result.setLimit(limit);
            result.setData(accountDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(BaseResult.CODE_SUCCESS);
        return result;
    }

    /**
     * 内部转账
     * @param balance
     * @param result
     * @param request
     * @return
     */
    @RequestMapping("/modifyOut.do")
    public BaseResult modifyOut(Double balance,String toname,String password2,BaseResult result, HttpServletRequest request) {
        String remoteUser = request.getRemoteUser();
        try {
            User user = userService.selectByUsername(remoteUser);
            if (!password2.equals(user.getPassword2())){
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("二级密码错误");
                return result;
            }
            User touser = userService.selectByUsername(toname);
            if (touser==null){
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("目标账户错误");
                return result;
            }
            UserAccount userAccount = userAccountService.selectByUid(user.getId());
            int i = userAccountService.modifyOut(userAccount,balance,touser.getId());
            if (i >= 3) {
                result.setCode(BaseResult.CODE_SUCCESS);
                result.setMsg("转账成功");
            } else {
                result.setCode(BaseResult.CODE_FAILED);
                result.setMsg("转账失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 提现
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/pass.do")
    public BaseResult pass(Integer id,Integer status,BaseResult result) {
        int pass = 0;
        try {
            pass= accountDetailService.pass(id, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pass>0){
            result.setCode(BaseResult.CODE_SUCCESS);
        }else {
            result.setCode(BaseResult.CODE_FAILED);
        }
        return result;
    }
}
