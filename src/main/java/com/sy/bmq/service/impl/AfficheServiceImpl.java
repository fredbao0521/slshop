package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.bmq.mapper.AfficheMapper;
import com.sy.bmq.mapper.GoodsMapper;
import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.CartGoods;
import com.sy.bmq.model.GoodsInfo;
import com.sy.bmq.model.base.BaseResult;
import com.sy.bmq.service.AfficheService;
import com.sy.bmq.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AfficheServiceImpl implements AfficheService {
    @Autowired
    private AfficheMapper afficheMapper;

    /**
     * 查询所有公告
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param result
     * @return
     * @throws Exception
     */
    @Override
    public List<Affiche> findAllAffiches(int pageNum, int pageSize,BaseResult result) throws Exception {
        List<Affiche> affiches = afficheMapper.findAll();
        result.setCount(affiches.size());
        PageHelper.startPage(pageNum, pageSize);
        return afficheMapper.findAll();
    }

    /**
     * 根据ID删除公告
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int removeAffiche(Integer id) throws Exception {
        return afficheMapper.removeAffiche(id);
    }
}
