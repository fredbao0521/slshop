package com.sy.zy.service;

import com.sy.bmq.model.DataDictionary;

import java.util.List;

public interface DictionaryService {
    Integer add(DataDictionary dataDictionary)throws Exception;

    /**
     * 分页查询多条数据
     * @param limit
     * @param page
     * @return
     * @throws Exception
     */
    List<DataDictionary> select(Integer limit, Integer page)throws Exception;
    Integer delete(int id)throws Exception;
    Integer update(DataDictionary dataDictionary)throws Exception;

    /**
     * 做重复性校验
     * @param typeCode
     * @param valueId
     * @return
     * @throws Exception
     */
    DataDictionary find1(String typeCode, int valueId)throws Exception;
    List<DataDictionary>  find2(DataDictionary dataDictionary)throws Exception;
}
