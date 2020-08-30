package com.sy.zy.service;

import com.sy.bmq.model.AuAuthority;

import java.util.List;

public interface AuthService {
    List<AuAuthority> find(int id)throws Exception;
    AuAuthority find1(int id)throws Exception;
    Integer delete(int parId)throws Exception;
}
