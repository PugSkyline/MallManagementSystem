package com.xdu.web.servlet;

import com.alibaba.fastjson.JSON;
import com.xdu.pojo.Employee;
import com.xdu.pojo.PageBean;
import com.xdu.service.EmployeeService;
import com.xdu.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/employee/*")
public class EmployeeServlet extends BaseServlet{

    private EmployeeService employeeService   = new EmployeeServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        //1.接收商场数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //JSON转为Mall对象
        Employee employee = JSON.parseObject(params, Employee.class);

        //2.调用service添加
        employeeService.add(employee);
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
        employeeService.deleteById(id);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取cookie数组
        String departmentId = "";
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie:cookies) {
            String name = cookie.getName();
            if("departmentId".equals(name)){
                String value = cookie.getValue();
                //System.out.println(value);
                departmentId = value;
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
        Employee employee = JSON.parseObject(params, Employee.class);
        employee.setDepartmentId(departmentId);

        //2.调用service查询
        PageBean<Employee> pageBean = employeeService.selectByPageAndCondition(currentPage, pageSize, employee);
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
        Employee employee = JSON.parseObject(params, Employee.class);
        //2.调用service添加
        employeeService.update(employee);
        //3.响应成功标识
        resp.getWriter().write("success");

    }


}
