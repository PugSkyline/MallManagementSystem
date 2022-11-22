package com.xdu.service;

import com.xdu.pojo.Department;
import com.xdu.pojo.PageBean;

public interface DepartmentService {

    void add(Department department);

    void deleteById(String id);

    PageBean<Department> selectByPageAndCondition(int currentPage, int pageSize, Department department);

    void update(Department department);

}
