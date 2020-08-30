package com.sy.zy.service;


import com.sy.bmq.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * 分页查询
     * @param limit
     * @param page
     * @return
     * @throws Exception
     */
    List<Role> select(Integer limit, Integer page)throws Exception;
    Integer delete(int id)throws Exception;
    Integer update(Role role)throws Exception;
    Integer insert(Role role)throws Exception;


}
