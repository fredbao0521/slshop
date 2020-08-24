package com.sy.bmq.service.impl;

import com.sy.bmq.mapper.UserMapper;
import com.sy.bmq.model.User;
import com.sy.bmq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    public User findByUsername(String username) throws Exception{
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", username);
        List<User> list = mapper.selectByExample(example);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

}
