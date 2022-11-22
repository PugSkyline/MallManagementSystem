package com.xdu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.xdu.pojo.Department;
import com.xdu.pojo.PageBean;
import com.xdu.service.DepartmentService;
import com.xdu.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/department/*")
public class DepartmentServlet extends BaseServlet{

    private DepartmentService departmentService  = new DepartmentServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //System.out.println("mall add...");
        //1.接收商场数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Department department = JSON.parseObject(params, Department.class);

        //2.调用service添加
        departmentService.add(department);
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
        departmentService.deleteById(id);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取cookie数组
        String mallId = "";
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie:cookies) {
            String name = cookie.getName();
            if("mallId".equals(name)){
                String value = cookie.getValue();
                //System.out.println(value);
                mallId = value;
                break;
            }
        }
        //System.out.println("mallId:"+mallId);

        //1.接受当前页码和每页展示条数 url?currenPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Department department = JSON.parseObject(params, Department.class);
        department.setMallId(mallId);

        //2.调用service查询
        PageBean<Department> pageBean = departmentService.selectByPageAndCondition(currentPage, pageSize, department);
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
        Department department = JSON.parseObject(params, Department.class);
        //2.调用service添加
        departmentService.update(department);
        //3.响应成功标识
        resp.getWriter().write("success");

    }

    public void gotoEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.接收商场数据
        BufferedReader br = req.getReader();
        String departmentId = br.readLine();
        Cookie cookie = new Cookie("departmentId", departmentId);
        cookie.setPath("/");
        resp.addCookie(cookie);
        //System.out.println("value:"+mallId);
        //3.响应成功标识
        resp.getWriter().write("success");

    }


}
