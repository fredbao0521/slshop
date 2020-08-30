package com.sy.zy.dao;


import com.sy.bmq.model.Report2;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Report {
    /**
     * 根据会员类型查看
     * @param type
     * @return
     * @throws Exception
     */
    @Select("select * from report2 where type=#{type}")
    List<Report2>  find(Integer type)throws Exception;


}
