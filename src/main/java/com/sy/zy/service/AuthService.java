package com.sy.zy.service;


import com.sy.bmq.model.AuAuthority;

import java.util.List;

public interface AuthService {
    /**
     * 根据roleId查询多条
     * @param id
     * @return
     * @throws Exception
     */
    List<AuAuthority> find(int id)throws Exception;

    /**
     * 根据functionId查询单条
     * @param id
     * @return
     * @throws Exception
     */
    AuAuthority find1(int id)throws Exception;
    Integer delete(int parId)throws Exception;
}
