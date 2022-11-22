package com.xdu.mapper;

import com.xdu.pojo.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeMapper {

    @Insert("insert into tb_employee values (#{departmentId}, #{id}, #{name}, #{post}, #{tel}, #{salary})")
    void add(Employee employee);

    @Delete("delete from tb_employee where id = #{id}")
    void deleteById(String id);

    List<Employee> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("employee") Employee employee);

    int selectTotalCountByCondition(Employee employee);

    @Update("update tb_employee set name = #{name}, post = #{post}, tel = #{tel}, salary = #{salary} where id = #{id}")
    void update(Employee employee);

}
