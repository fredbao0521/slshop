package com.sy.zy.service;

import com.sy.bmq.model.AccountDetail;

import java.util.List;

public interface AwardService {
    List<AccountDetail> select()throws Exception;
}
