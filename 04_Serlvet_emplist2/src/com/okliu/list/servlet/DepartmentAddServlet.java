package com.okliu.list.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.DepartmentDaoHelper;
import com.okliu.list.entity.Department;

public class DepartmentAddServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数的编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");
        
        //2、接收请求参数
        String deptName = req.getParameter("deptName");
        //2.1 如果需要封装对象，则创建对象并封装
        Department dept = new Department();
        dept.setDeptName(deptName);
        
        //3、调用后端的业务代码执行对应需要的操作
        DepartmentDaoHelper helper = new DepartmentDaoHelper();
        boolean saved = helper.insert(dept);
        
        //4、根据后端业务返回，处理前台控制
        if(saved) {//成功
          //重定向到列表页面
          resp.sendRedirect("deptList.do");  
        } else {//失败 重新回到添加页面
          resp.sendRedirect("deptAdd.html");  
        }
        
    }
    
}
