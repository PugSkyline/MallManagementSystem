package com.xdu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.xdu.pojo.Mall;
import com.xdu.pojo.PageBean;
import com.xdu.service.MallService;
import com.xdu.service.impl.MallServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/mall/*")
public class MallServlet extends BaseServlet{

    private MallService mallService = new MallServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("mall selectAll...");
        //1.调用service查询
        List<Mall> malls = mallService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(malls);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //System.out.println("mall add...");
        //1.接收商场数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Mall mall = JSON.parseObject(params, Mall.class);
        //2.调用service添加
        mallService.add(mall);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //1.接收商场数据
        BufferedReader br = req.getReader();
        String id = br.readLine();//json字符串
        //JSON转为int
        //String id = JSON.parseObject(params, String.class);
        //2.调用service添加
        mallService.deleteById(id);
        //3.响应成功标识
        resp.getWriter().write("success");
    }


    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接受当前页码和每页展示条数 url?currenPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Mall mall = JSON.parseObject(params, Mall.class);

        //2.调用service查询
        PageBean<Mall> pageBean = mallService.selectByPageAndCondition(currentPage, pageSize, mall);
        //3.转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //4.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收商场数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Mall mall = JSON.parseObject(params, Mall.class);
        //2.调用service添加
        mallService.update(mall);
        //3.响应成功标识
        resp.getWriter().write("success");

    }

    public void gotoDepartment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收商场数据
        BufferedReader br = req.getReader();
        String mallId = br.readLine();
        Cookie cookie = new Cookie("mallId", mallId);
        cookie.setPath("/");
        resp.addCookie(cookie);
        //System.out.println("value:"+mallId);
        //3.响应成功标识
        resp.getWriter().write("success");

    }


}
