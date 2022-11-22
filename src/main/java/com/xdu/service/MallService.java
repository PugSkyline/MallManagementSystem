package com.xdu.service;

import com.xdu.pojo.Mall;
import com.xdu.pojo.PageBean;

import java.util.List;

public interface MallService {

    List<Mall> selectAll();

    void add(Mall mall);

    void deleteById(String id);

    PageBean<Mall> selectByPageAndCondition(int currentPage, int pageSize, Mall mall);

    void update(Mall mall);

}
