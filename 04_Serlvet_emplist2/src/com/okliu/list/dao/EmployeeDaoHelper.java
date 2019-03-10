package com.okliu.list.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.okliu.list.entity.Department;
import com.okliu.list.entity.Employee;
import com.okliu.list.util.DBUtils;

/**
 * <p>Title: EmployeeDaoHelper.java</p>
 * <p>Description: 数据库操作对象</p>
 * @author liuhaolie
 * @date 2019-02-21 10:46
 * @version 1.0
 */
public class EmployeeDaoHelper {

    /**
     * 添加员工
     * @param e
     */
    public boolean insert(Employee e) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "insert into tb_employee(emp_name,dept_id) values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, e.getName());
            pstmt.setInt(2, e.getDeptId());
            //3、执行语句
            return transactionUpdate(conn, pstmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt, null);
        }
        return false;
    }


    /***
     * 事务控制的操作
     * @param conn
     * @param pstmt
     * @return
     * @throws SQLException
     */
    private boolean transactionUpdate(Connection conn,
            PreparedStatement pstmt) throws SQLException {
        conn.setAutoCommit(false);//关闭自动提交
        int insertRows = pstmt.executeUpdate();
        if(insertRows != 1) {//有问题，数据回滚，加入数据库事务
            conn.rollback();//回滚事务
            return false;
        } else {
            conn.commit();//提交事务
            return true;
        }
    }
    
    
    /**
     * 删除员工
     * @param id
     */
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "delete from tb_employee where emp_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return transactionUpdate(conn, pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt, null);
        }
        return false;
    }
    
    /**
     * 修改员工
     * @param e
     */
    public boolean update(Employee e) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "update tb_employee set emp_name=?,dept_id=? where emp_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, e.getName());
            pstmt.setInt(2, e.getDeptId());
            pstmt.setInt(3, e.getId());
            return transactionUpdate(conn, pstmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtils.close(conn, pstmt, null);
        }
        return false;
    }
    
    /**
     * 查一个
     * @param id
     * @return
     */
    public Employee getOne(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        try {
            //1、获取数据库连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "select e.*,d.dept_name from tb_employee e,tb_department d where e.dept_id=d.dept_id and emp_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            //3、执行语句，处理结果
            rst = pstmt.executeQuery();
            if(rst.next()) {
                Employee e = new Employee();
                e.setId(rst.getInt("emp_id"));
                e.setName(rst.getString("emp_name"));
                e.setDeptId(rst.getInt("dept_id"));
                Department d = new Department();
                d.setDeptId(rst.getInt("dept_id"));
                d.setDeptName(rst.getString("dept_name"));
                e.setDept(d);//把部门对象封装上去
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn, pstmt, rst);//4、释放数据库资源
        }
        return null;
    }
    
    /**
     * 查询所有
     * @return
     */
    public List<Employee> getAll(){
        /*
         * 对于集合容器，如果没有数据，不要返回null，返回一个空容器
         * 即，size=0
         */
        List<Employee> emps = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
            //1、获取数据库连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "select e.*,d.dept_name from tb_employee e,tb_department d where e.dept_id=d.dept_id";
            stmt = conn.createStatement();
            //3、执行语句，处理结果
            rst = stmt.executeQuery(sql);
            while(rst.next()) {
                Employee e = new Employee();
                e.setId(rst.getInt("emp_id"));
                e.setName(rst.getString("emp_name"));
                e.setDeptId(rst.getInt("dept_id"));
                
                Department d = new Department();
                d.setDeptId(rst.getInt("dept_id"));
                d.setDeptName(rst.getString("dept_name"));
                
                e.setDept(d);//把部门对象封装上去
                
                emps.add(e);//加入到集合
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(conn, stmt, rst);//4、释放数据库资源
        }
        return emps;
    }
    
}
