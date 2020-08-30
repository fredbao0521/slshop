package com.sy.bmq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.bmq.mapper.AfficheMapper;
import com.sy.bmq.mapper.GoodsMapper;
import com.sy.bmq.model.*;
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

    /**
     * 添加公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int addAffiche(Affiche affiche) throws Exception {
        return afficheMapper.addAffiche(affiche);
    }

    /**
     * 更新公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateAffiche(Affiche affiche) throws Exception {
        return afficheMapper.updateAffiche(affiche);
    }

    /**
     * 根据创建人名字查询
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public List<LeaveMessage> findMessByName(int pageNum, int pageSize,String username,BaseResult result) throws Exception {
        List<LeaveMessage> leaveMessages = afficheMapper.findMessByName(username);
        result.setCount(leaveMessages.size());
        PageHelper.startPage(pageNum, pageSize);
        return afficheMapper.findMessByName(username);
    }

    /**
     * 查询所有留言
     * @param pageNum
     * @param pageSize
     * @param result
     * @return
     * @throws Exception
     */
    @Override
    public List<LeaveMessage> findAllMess(int pageNum, int pageSize, BaseResult result) throws Exception {
        List<LeaveMessage> allMess = afficheMapper.findAllMess();
        result.setCount(allMess.size());
        PageHelper.startPage(pageNum, pageSize);
        return afficheMapper.findAllMess();
    }

    /**
     * 新建留言
     * @param leaveMessage
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int saveMess(LeaveMessage leaveMessage) throws Exception {
        return afficheMapper.saveMess(leaveMessage);
    }

    /**
     * 根据留言的Id删除留言
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int removeById(Integer id) throws Exception {
        return afficheMapper.removeById(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int saveMessage(LeaveReply leaveReply) throws Exception {
        return afficheMapper.saveMessage(leaveReply);
    }


    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public int updateMessage(Integer id) throws Exception {
        return afficheMapper.updateMessage(id);
    }
}
