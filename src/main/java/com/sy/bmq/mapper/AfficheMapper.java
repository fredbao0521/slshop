package com.sy.bmq.mapper;

import com.sy.bmq.model.Affiche;
import com.sy.bmq.model.LeaveMessage;
import com.sy.bmq.model.LeaveReply;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AfficheMapper extends Mapper<Affiche> {

    /**
     * 查询所有公告
     * @return
     * @throws Exception
     */
    @Select("SELECT * FROM affiche order by publishTime DESC")
    List<Affiche> findAll() throws Exception;

    /**
     * 根据ID删除公告
     * @param id
     * @return
     * @throws Exception
     */
    @Delete("DELETE FROM affiche WHERE id = #{id}")
    int removeAffiche(Integer id) throws Exception;

    /**
     * 新增公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Insert("insert into affiche(title,content,publisher,publishTime,startTime,endTime) values(#{title},#{content},#{publisher},now(),#{startTime},#{endTime})")
    int addAffiche(Affiche affiche)throws Exception;

    /**
     * 更新公告
     * @param affiche
     * @return
     * @throws Exception
     */
    @Update("update affiche set title = #{title},content = #{content},publishTime = now(),startTime = #{startTime},endTime = #{endTime} where id = #{id}")
    int updateAffiche(Affiche affiche)throws Exception;

    /**
     * 根据留言人名字查询留言
     * 查自己的留言
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from leave_message where createdBy = #{username}")
     List<LeaveMessage> findMessByName(String username) throws Exception;

    /**
     * 查询所有留言
     * 管理员用来回复
     * @return
     * @throws Exception
     */
    @Select("select * from leave_message ")
    List<LeaveMessage> findAllMess() throws Exception;


    /**
     * 新增留言
     * @param leaveMessage
     * @return
     * @throws Exception
     */
    @Insert("insert into leave_message(createdBy,messageCode,messageTitle,messageContent,state,createTime) values(#{createdBy},#{messageCode},#{messageTitle},#{messageContent},0,now())")
    int saveMess(LeaveMessage leaveMessage) throws Exception;

    /**
     * 根据ID删除留言
     * @param id
     * @return
     * @throws Exception
     */
    @Delete("delete from leave_message where id = #{id}")
    int removeById(Integer id) throws Exception;


    /**
     * 新增留言回复
     * @param leaveReply
     * @return
     * @throws Exception
     */
    @Insert("insert into leave_reply(messageId,replyContent,createdBy,createTime) values(#{messageId},#{replyContent},#{createdBy},now())")
    int saveMessage(LeaveReply leaveReply) throws Exception;



    /**
     * 更新留言回复状态
     * @param id
     * @return
     * @throws Exception
     */
    @Update("update leave_message set state = 1 where id = #{id}")
    int updateMessage(Integer id)throws Exception;
}
