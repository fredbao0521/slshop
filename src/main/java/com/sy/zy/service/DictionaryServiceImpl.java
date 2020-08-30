package com.sy.zy.service;

import com.github.pagehelper.PageHelper;
import com.sy.zy.dao.DictionaryDao;
import com.sy.bmq.model.DataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryDao dictionaryDao;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer add(DataDictionary dataDictionary) throws Exception {
        return dictionaryDao.add(dataDictionary);
    }

    @Override
    public List<DataDictionary> select(Integer limit,Integer page) throws Exception {
        if (limit!=null){
            PageHelper.startPage(page,limit);
        }
        return dictionaryDao.find();
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer delete(int id) throws Exception {
        return dictionaryDao.remove(id);
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public Integer update(DataDictionary dataDictionary) throws Exception {
        return dictionaryDao.modify(dataDictionary);
    }

    @Override
    public DataDictionary find1(String typeCode, int valueId) throws Exception {
        return dictionaryDao.find1(typeCode,valueId);
    }


    @Override
    public List<DataDictionary>  find2(DataDictionary dataDictionary) throws Exception {
        return dictionaryDao.find2(dataDictionary);
    }
}
