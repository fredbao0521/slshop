package com.sy.zy.service;

import com.sy.bmq.model.User;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;


public interface UserService {
    Integer Insert(User user)throws Exception;
    List<User> selectAll(User user)throws Exception;
    List<User> selectPage(User user, Integer limit, Integer page)throws Exception;
    List<User> selectWithWhere(int pageNum, int pageSize, User user, BaseResult result) throws Exception;
    Integer updatepwd(User user)throws Exception;
    Integer delete(int id)throws Exception;
    Integer updateMess(User user)throws Exception;
    List<User> select(User user, Integer limit, Integer page)throws Exception;
    Integer selectCount(String roleName)throws Exception;
    /**
     * 修改审核状态的
     * @param id
     * @return
     * @throws Exception
     */
    Integer updateone(Integer id) throws Exception;

    User selectByUsername(String username) throws Exception;
}
