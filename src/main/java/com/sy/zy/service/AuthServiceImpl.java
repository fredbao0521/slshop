package com.sy.zy.service;

import com.sy.bmq.model.AuAuthority;
import com.sy.zy.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthDao authDao;
    @Override
    public List<AuAuthority> find(int id) throws Exception {
        return authDao.find(id);
    }


    @Override
    public AuAuthority find1(int id) throws Exception {
        return authDao.find1(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer delete(int parId) throws Exception {
        return authDao.remove(parId);
    }
}
