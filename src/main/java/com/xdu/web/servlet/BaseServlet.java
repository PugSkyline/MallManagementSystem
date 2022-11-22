package com.xdu.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求路径
        String uri = req.getRequestURI();
        //2.取最后一段路径，即方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
        //3.执行方法
        //3.1 获取 MallServlet / DepartmentServlet / EmployeeServlet 字节码对象 Class
        //谁调用我(this所在的类),我(this)代表谁
        Class<? extends BaseServlet> cls = this.getClass();
        //3.2获取方法Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //3.3执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
