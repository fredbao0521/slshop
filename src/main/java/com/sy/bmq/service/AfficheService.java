package com.sy.bmq.service;

import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface AfficheService {

    List<Affiche> findAllAffiches(int pageNum,int pageSize,BaseResult result) throws Exception;

    int removeAffiche(Integer id) throws Exception;
}
