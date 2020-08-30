package com.sy.zy.service;

import com.github.pagehelper.PageHelper;
import com.sy.zy.dao.UserDao;
import com.sy.bmq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserSeriveImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer Insert(User user) throws Exception {
        return userDao.add(user);
    }

    @Override
    public List<User> selectAll(User user) throws Exception {
        return userDao.find(user);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer updatepwd(User user) throws Exception {
        return userDao.modify(user);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer delete(int id) throws Exception {
        return userDao.remove(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer updateMess(User user) throws Exception {
        return userDao.modifyMess(user);
    }

    @Override
    public List<User> selectPage(User user, Integer limit, Integer page) throws Exception {
        if (limit !=null){
            PageHelper.startPage(page,limit);
        }
        return userDao.find(user);
    }

    @Override
    public List<User> select(User user, Integer page,Integer limit) throws Exception {
        if (limit !=null){
            PageHelper.startPage(page,limit);
        }
        return userDao.select(user);
    }

    @Override
    public Integer selectCount(String roleName) throws Exception {
        return userDao.selectCount(roleName);
    }
}
