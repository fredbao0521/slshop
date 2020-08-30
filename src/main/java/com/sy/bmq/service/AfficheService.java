package com.sy.bmq.service;

import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.LeaveMessage;
import com.sy.bmq.model.LeaveReply;
import com.sy.bmq.model.base.BaseResult;

import java.util.List;

public interface AfficheService {

    List<Affiche> findAllAffiches(int pageNum,int pageSize,BaseResult result) throws Exception;

    int removeAffiche(Integer id) throws Exception;

    int addAffiche(Affiche affiche)throws Exception;

    int updateAffiche(Affiche affiche)throws Exception;

    List<LeaveMessage> findMessByName(int pageNum, int pageSize,String username,BaseResult result) throws Exception;

    List<LeaveMessage> findAllMess(int pageNum, int pageSize,BaseResult result) throws Exception;

    int saveMess(LeaveMessage leaveMessage) throws Exception;

    int removeById(Integer id) throws Exception;

    int saveMessage(LeaveReply leaveReply)throws Exception;

    int updateMessage(Integer state)throws Exception;
}
