package com.okliu.list.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.EmployeeDaoHelper;

public class EmployeeDeleteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数的编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");
        
        //2、接收请求参数
        int id = Integer.parseInt(req.getParameter("id"));
        
        //3、调用后端的业务代码执行对应需要的操作
        EmployeeDaoHelper helper = new EmployeeDaoHelper();
        boolean deleted = helper.delete(id);
        
        //4、根据后端业务返回，处理前台控制
        if(deleted) {//成功删除
          //重定向到列表页面
          resp.sendRedirect("empList.do");  
        } else {//删除失败
          resp.getWriter().println("<h3>删除失败</h3><a href=\"empList.do\">返回</a>");  
        }
        
    }
    
}
