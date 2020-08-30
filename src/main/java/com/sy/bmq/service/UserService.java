package com.sy.bmq.service;

import com.sy.bmq.model.User;

public interface UserService {
    User findByUsername(String username) throws Exception;

    User selectByUsername(String username) throws Exception;

    int updateUser(User user) throws Exception;

    User selectByReferCode(String referCode) throws Exception;
}
