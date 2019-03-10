package com.okliu.list.servlet.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okliu.list.dao.DepartmentDaoHelper;
import com.okliu.list.entity.Department;
import com.okliu.list.util.JsonUtils;

/**
 * <p>Title: DempartmentListAjaxServlet.java</p>
 * <p>Description: 用于异步数据交互的ajax+json服务器</p>
 * @author liuhaolie
 * @date 2019-03-03 14:39
 * @version 1.0
 */
@WebServlet("/ajax/depts.do")
public class DempartmentListAjaxServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得部门列表
        DepartmentDaoHelper helper = new DepartmentDaoHelper();
        List<Department> depts = helper.getAll();
        
        //java对象转换成json数据格式
        String jsonData = JsonUtils.toJson(depts);
        
        //发送json数据回去
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().print(jsonData);
    }
    
}
