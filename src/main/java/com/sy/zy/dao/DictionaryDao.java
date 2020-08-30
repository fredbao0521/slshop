package com.sy.zy.dao;


import com.sy.bmq.model.DataDictionary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DictionaryDao {
    @Insert("insert into data_dictionary (typeCode,typeName,valueId,valueName) values (#{typeCode},#{typeName},#{valueId},#{valueName})")
    Integer add(DataDictionary dataDictionary)throws Exception;

    @Select("select * from data_dictionary ")
    List<DataDictionary> find()throws Exception;


    /*根据id删除*/
    @Delete("delete from data_dictionary where id=#{id}")
    Integer remove(int id)throws Exception;

    @Update("update data_dictionary set typeCode=#{typeCode},typeName=#{typeName},valueId=#{valueId},valueName=#{valueName} where id=#{id}")
    Integer modify(DataDictionary dataDictionary)throws Exception;


    /**
     * 根据valueId和typecode来进重复性校验
     * @param typeCode
     * @param valueId
     * @return
     * @throws Exception
     */
    @Select("select * from data_dictionary where valueId=#{valueId} and typeCode=#{typeCode}")
    DataDictionary find1(String typeCode, int valueId)throws Exception;



    @Select("<script> " +
            "SELECT * " +
            "FROM data_dictionary " +
            " <where> " +
            " <if test=\"typeCode != '' and typeCode!=null\"> typeCode= #{typeCode}</if> " +
            " <if test=\"valueName != '' and valueName!=null\"> valueName= #{valueName}</if> " +
            " <if test=\"valueId >0\"> valueId= #{valueId}</if> " +
            " <if test=\"id >0\"> id= #{id}</if> " +
            " </where> " +
            " </script> ")
    List<DataDictionary> find2(DataDictionary dataDictionary)throws Exception;


}
