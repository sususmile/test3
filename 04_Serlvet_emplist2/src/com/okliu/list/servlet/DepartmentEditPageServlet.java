package com.okliu.list.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.DepartmentDaoHelper;
import com.okliu.list.dao.EmployeeDaoHelper;
import com.okliu.list.entity.Department;
import com.okliu.list.entity.Employee;

public class DepartmentEditPageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数的编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");
        
        //2、接收请求参数
        int id = Integer.parseInt(req.getParameter("id"));
        
        //3、调用后端的业务代码执行对应需要的操作
        DepartmentDaoHelper helper = new DepartmentDaoHelper();
        Department dept = helper.getOne(id);
        
        //4、根据后端业务返回，显示前台页面
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Insert title here</title>");
        out.println("<style type=\"text/css\">");
        out.println("   .readonly{background-color: rgb(209,209,209);}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align=\"center\">部门管理系统-编辑</h1>");
        out.println("<center>");
        out.println("   <form action=\"deptEdit.do\" method=\"post\">");
        out.println("       <p>部门编号：<input type=\"number\" name=\"deptId\" value=\""+dept.getDeptId()+"\"/></p>");
        out.println("       <p>部门名称：<input type=\"text\" name=\"deptNa\" value=\""+dept.getDeptName()+"\"/></p>");
        out.println("       <p><input type=\"submit\" value=\"编辑\"/></p>");
        out.println("   </form>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        
        
    }
    
}
