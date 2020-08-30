package com.sy.zy.service;

import com.sy.bmq.model.User;

import java.util.List;


public interface UserService {
    Integer Insert(User user)throws Exception;
    List<User> selectAll(User user)throws Exception;
    List<User> selectPage(User user, Integer limit, Integer page)throws Exception;
    Integer updatepwd(User user)throws Exception;
    Integer delete(int id)throws Exception;
    Integer updateMess(User user)throws Exception;
    List<User> select(User user, Integer limit, Integer page)throws Exception;
    Integer selectCount(String roleName)throws Exception;
}
