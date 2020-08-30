package com.sy.bmq.mapper;

import com.sy.bmq.model.Information;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface InformationMapper extends Mapper<Information> {
    @Update("update  information set state=1 where id=?")
    int modify(int id) throws Exception;

    @Select("select * from information")
    List<Information> findAll() throws Exception;

    @Insert("INSERT into information (title,content,state,publisher,publishTime,filePath,uploadTime)" +
            "VALUES(#{title},#{content},#{state},#{publisher},now(),#{filePath},now())")
    int insert(Information information);


    @Delete("delete from information where id =#{id}")
    void deleteById(int id) throws Exception;
}
