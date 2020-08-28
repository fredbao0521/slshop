package com.sy.bmq.mapper;

import com.sy.bmq.model.Affiche;
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
}
