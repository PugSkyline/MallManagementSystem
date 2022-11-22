package com.xdu.service.impl;

import com.xdu.mapper.MallMapper;
import com.xdu.pojo.Mall;
import com.xdu.pojo.PageBean;
import com.xdu.service.MallService;
import com.xdu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MallServiceImpl implements MallService {

    //1.创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Mall> selectAll() {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        MallMapper mapper = sqlSession.getMapper(MallMapper.class);
        //4.调用方法
        List<Mall> malls = mapper.selectAll();
        //5.释放资源
        sqlSession.close();

        return malls;
    }

    public void add(Mall mall) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        MallMapper mapper = sqlSession.getMapper(MallMapper.class);
        //4.调用方法
        mapper.add(mall);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }

    public void deleteById(String id){

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        MallMapper mapper = sqlSession.getMapper(MallMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }


    public PageBean<Mall> selectByPageAndCondition(int currentPage, int pageSize, Mall mall) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        MallMapper mapper = sqlSession.getMapper(MallMapper.class);
        //4.计算开始索引和查询条目数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //处理mall条件，模糊表达式
        String name = mall.getName();
        if(name != null && name.length() > 0){
            mall.setName("%"+name+"%");
        }
        String addr = mall.getAddr();
        if(addr != null && addr.length() > 0){
            mall.setAddr("%"+addr+"%");
        }
        //5.调用方法
        List<Mall> rows = mapper.selectByPageAndCondition(begin, size, mall);
        int totalCount = mapper.selectTotalCountByCondition(mall);
        //6.封装pageBean对象
        PageBean<Mall> pageBean = new PageBean<Mall>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //7.释放资源
        sqlSession.close();
        return pageBean;
    }

    public void update(Mall mall) {

        //2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取MallMapper
        MallMapper mapper = sqlSession.getMapper(MallMapper.class);
        //4.调用方法
        mapper.update(mall);
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

    }
}
