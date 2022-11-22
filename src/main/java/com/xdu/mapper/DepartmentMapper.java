package com.xdu.mapper;

import com.sun.org.apache.bcel.internal.generic.DDIV;
import com.xdu.pojo.Department;
import com.xdu.pojo.Mall;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentMapper {

    @Insert("insert into tb_department values (#{mallId}, #{id}, #{name}, #{addr}, #{tel})")
    void add(Department department);

    @Delete("delete from tb_department where id = #{id}")
    void deleteById(String id);

    List<Department> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("department") Department department);

    int selectTotalCountByCondition(Department department);

    @Update("update tb_department set name = #{name}, addr = #{addr}, tel = #{tel} where id = #{id}")
    void update(Department department);

}
