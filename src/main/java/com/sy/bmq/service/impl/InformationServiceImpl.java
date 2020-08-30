package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.sy.bmq.mapper.InformationMapper;
import com.sy.bmq.model.Information;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;
    @Override
    public List<Information> findAll(int pageNum, int pageSize, BaseResult result) throws Exception {
        List<Information> informationList = informationMapper.findAll();
        result.setCount(informationList.size());
        PageHelper.startPage(pageNum, pageSize);
        return informationMapper.findAll();
    }


    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int insert(Information information) throws Exception {
        return informationMapper.insert(information);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public void remove(Integer id) throws Exception {
        informationMapper.deleteById(id);

    }
}
