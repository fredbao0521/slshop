package com.sy.zy.service;

import com.github.pagehelper.PageHelper;
import com.sy.bmq.model.Role;
import com.sy.zy.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> select(Integer limit, Integer page) throws Exception {
        if (limit !=null){
            PageHelper.startPage(page,limit);
        }
        return roleDao.find();
    }

@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer delete(int id) throws Exception {
        return roleDao.remove(id);
    }


    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer update(Role role) throws Exception {
        return roleDao.modify(role);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public Integer insert(Role role) throws Exception {
        return roleDao.add(role);
    }

}
