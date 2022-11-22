package com.xdu.service.impl;

import com.xdu.mapper.EmployeeMapper;
import com.xdu.pojo.Employee;
import com.xdu.pojo.PageBean;
import com.xdu.service.EmployeeService;
import com.xdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    //1.创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Employee employee) {
        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //4.调用方法
        mapper.add(employee);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void deleteById(String id) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    public PageBean<Employee> selectByPageAndCondition(int currentPage, int pageSize, Employee employee) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //4.计算开始索引和查询条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //5.调用方法
        List<Employee> rows = mapper.selectByPageAndCondition(begin, size, employee);
        //System.out.println(rows);
        int totalCount = mapper.selectTotalCountByCondition(employee);
        //6.封装pageBean对象
        PageBean<Employee> pageBean = new PageBean<Employee>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setDepartmentId(employee.getDepartmentId());
        //7.释放资源
        sqlSession.close();
        return pageBean;
    }

    public void update(Employee employee) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //4.调用方法
        mapper.update(employee);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }
}
