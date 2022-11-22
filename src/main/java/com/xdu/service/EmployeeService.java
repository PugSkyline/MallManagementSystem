package com.xdu.service;

import com.xdu.pojo.Employee;
import com.xdu.pojo.PageBean;

public interface EmployeeService {

    void add(Employee employee);

    void deleteById(String id);

    PageBean<Employee> selectByPageAndCondition(int currentPage, int pageSize, Employee employee);

    void update(Employee employee);

}
