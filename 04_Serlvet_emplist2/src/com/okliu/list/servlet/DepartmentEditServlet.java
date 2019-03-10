package com.okliu.list.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.DepartmentDaoHelper;
import com.okliu.list.dao.EmployeeDaoHelper;
import com.okliu.list.entity.Department;
import com.okliu.list.entity.Employee;

public class DepartmentEditServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数的编码
        req.setCharacterEncoding("utf-8");
        //设置响应编码
        resp.setContentType("text/html; charset=utf-8");
        
//        2、接收请求参数
        int deptId  =Integer.parseInt(req.getParameter("deptId")) ;
        String deptNa = req.getParameter("deptNa");
        //2.1 如果需要封装对象，则创建对象并封装
        Department d = new Department();
        d.setDeptName(deptNa);
        d.setDeptId(deptId);
        
        //3、调用后端的业务代码执行对应需要的操作
        DepartmentDaoHelper helper = new DepartmentDaoHelper();
        boolean updated = helper.update(d);
        
        //4、根据后端业务返回，处理前台控制
        if(updated) {//成功
          //重定向到列表页面
          resp.sendRedirect("deptList.do");  
        } else {//失败 重新回到修改页面
          resp.sendRedirect("deptEditPage.do?id="+deptId);  
        }
        
    }
    
}
