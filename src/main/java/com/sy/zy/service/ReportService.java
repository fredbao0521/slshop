package com.sy.zy.service;


import com.sy.bmq.model.Report2;

import java.util.List;

public interface ReportService {

    /**
     * 根据类型查询多条
     * @param type
     * @return
     * @throws Exception
     */
    List<Report2> find(Integer type)throws Exception;
}
