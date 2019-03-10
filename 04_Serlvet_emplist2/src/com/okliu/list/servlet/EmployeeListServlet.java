package com.okliu.list.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.EmployeeDaoHelper;
import com.okliu.list.entity.Employee;

public class EmployeeListServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置接收参数编码
        req.setCharacterEncoding("utf-8");

        //获取daoHelper对象，用来操作数据库
        EmployeeDaoHelper helper = new EmployeeDaoHelper();
        //操作数据库
        List<Employee> emps = helper.getAll();

        //设置输出编码
        resp.setContentType("text/html; charset=utf-8");//html

        //获得输出对象
        PrintWriter out = resp.getWriter();
        //将数据显示在html页面上
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>Insert title here</title>");
        out.println("<style type=\"text/css\">");
        out.println("    th,td{text-align: center;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align=\"center\">员工管理系统-员工管理</h1>");
        out.println("<p align=\"center\"><a href=\"deptList.do\">部门管理</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"empAdd.html\">添加员工</a></p>");
        out.println("    <table border=\"1\" width=\"600\" align=\"center\">");
        out.println("        <tr><th>员工工号</th><th>员工姓名</th><th>部门编号</th><th>操作</th></tr>");
        for (Employee e : emps) {
            out.println("        <tr>"
                    + "<td>"+e.getId()+"</td>"
                    + "<td>"+e.getName()+"</td>"
                    + "<td>"+e.getDept().getDeptName()+"</td>"
                    + "<td><a onclick=\"return confirm('删除后无法恢复，确定要删除吗？')\"  href=\"empDelete.do?id="+e.getId()+"\">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"empEditPage.do?id="+e.getId()+"\">编辑</a></td>"
                    + "</tr>");
        }
        out.println("    </table>");
        out.println("");
        out.println("</body>");
        out.println("</html>");
    }

}
