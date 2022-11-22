package com.xdu.mapper;

import com.xdu.pojo.Mall;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MallMapper {

    /**
     * 查询所有
     */
    @Select("select * from tb_mall")
    List<Mall> selectAll();

    /**
     * 添加商场
     */
    @Insert("insert into tb_mall values (#{id}, #{name}, #{addr}, #{tel})")
    void add(Mall mall);

    /**
     * 根据id删除商场
     * @param id 商场编号
     */
    @Delete("delete from tb_mall where id = #{id}")
    void deleteById(String id);

    /**
     * 分页查询
     * @param begin 起始
     * @param size  条数
     */
    List<Mall> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("mall") Mall mall);

    /**
     * 查询总记录数
     */
    int selectTotalCountByCondition(Mall mall);

    /**
     * 更新商场
     */
    @Update("update tb_mall set name = #{name}, addr = #{addr}, tel = #{tel} where id = #{id}")
    void update(Mall mall);
}
