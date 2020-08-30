package com.sy.bmq.service;

import com.sy.bmq.model.Information;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface InformationService {
    List<Information> findAll(int pageNum, int pageSize, BaseResult result) throws  Exception;

    int insert(Information information) throws Exception;

    void  remove(Integer id) throws Exception;
}
