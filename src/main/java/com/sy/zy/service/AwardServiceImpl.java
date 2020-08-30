package com.sy.zy.service;

import com.sy.bmq.model.AccountDetail;
import com.sy.zy.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {
    @Autowired
    AccountDao accountDao;

    @Override
    public List<AccountDetail> select() throws Exception {
        return accountDao.select();
    }
}
