package com.sy.bmq.mapper;

import com.sy.bmq.model.GoodsInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsMapper extends Mapper<GoodsInfo> {

    @Select("SELECT * FROM goods_info order by id asc")
    List<GoodsInfo> selectAllGoods() throws Exception;

    @Select({"<script>" +
            "SELECT " +
            " * " +
            " from goods_info" +
            "<where>",
            "<if test=\'goodsName != null and goodsName != \"\"\'>"+
                    "and goodsName like concat('%',#{goodsName},'%')"+
                    "</if>" +
            "<if test=\'goodsSN != null and goodsSN != \"\"\'>"+
                    "and goodsSN like concat('%',#{goodsSN},'%')"+
                    "</if>" +
            "<if test=\'note != null and note != \"\"\'>"+
                    "and note like concat('%',#{note},'%')"+
                    "</if>" +
                    "</where></script>"
    })
//    List<GoodsInfo> selectWithWhere(GoodsInfo goodsInfo) throws Exception;
    List<GoodsInfo> selectWithWhere(@Param("goodsName") String goodsName,
                                    @Param("goodsSN") String goodsSN,
                                    @Param("note") String note) throws Exception;

    @Delete("Delete FROM goods_info where id = #{id}")
    int deleteGood(Integer id) throws Exception;

}
