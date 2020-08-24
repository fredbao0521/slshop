package com.sy.bmq.service;


import com.sy.bmq.model.User;

public interface UserService {
    public User findByUsername(String username) throws Exception;
}
