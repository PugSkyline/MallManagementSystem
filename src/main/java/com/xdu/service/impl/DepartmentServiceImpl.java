package com.xdu.service.impl;

import com.xdu.mapper.DepartmentMapper;
import com.xdu.pojo.Department;
import com.xdu.pojo.PageBean;
import com.xdu.service.DepartmentService;
import com.xdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
public class DepartmentServiceImpl implements DepartmentService {

    //1.创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Department department) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        //4.调用方法
        mapper.add(department);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    public void deleteById(String id){

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取Mapper
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    public PageBean<Department> selectByPageAndCondition(int currentPage, int pageSize, Department department) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        //4.计算开始索引和查询条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //5.调用方法
        List<Department> rows = mapper.selectByPageAndCondition(begin, size, department);
        //System.out.println(rows);
        int totalCount = mapper.selectTotalCountByCondition(department);
        //6.封装pageBean对象
        PageBean<Department> pageBean = new PageBean<Department>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setMallId(department.getMallId());
        //7.释放资源
        sqlSession.close();
        return pageBean;
    }

    public void update(Department department) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        //4.调用方法
        mapper.update(department);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

}
