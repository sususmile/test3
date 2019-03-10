package com.okliu.list.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.okliu.list.entity.Department;
import com.okliu.list.util.DBUtils;

/**
 * <p>Title: DepartmentDaoHelper.java</p>
 * <p>Description: 数据库操作对象</p>
 * @author liuhaolie
 * @date 2019-03-03
 * @version 1.1
 */
public class DepartmentDaoHelper {

    /**
     * 添加部门
     * @param e
     */
    public boolean insert(Department d) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "insert into tb_department(dept_id,dept_name) values(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, d.getDeptId());
            pstmt.setString(2, d.getDeptName());
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
     * 删除部门
     * @param id
     */
    public boolean delete(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "delete from tb_department where dept_id=?";
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
     * 修改部门
     * @param e
     */
    public boolean update(Department e) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //1、连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "update tb_department set dept_name=? where dept_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, e.getDeptName());
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
    public Department getOne(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        try {
            //1、获取数据库连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "select * from tb_department where dept_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            //3、执行语句，处理结果
            rst = pstmt.executeQuery();
            if(rst.next()) {
                Department e = new Department();
                e.setDeptId(rst.getInt("dept_id"));
                e.setDeptName(rst.getString("dept_name"));
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
    public List<Department> getAll(){
        /*
         * 对于集合容器，如果没有数据，不要返回null，返回一个空容器
         * 即，size=0
         */
        List<Department> emps = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rst = null;
        try {
            //1、获取数据库连接对象
            conn = DBUtils.getConnection();
            //2、语句对象
            String sql = "select * from tb_department";
            stmt = conn.createStatement();
            //3、执行语句，处理结果
            rst = stmt.executeQuery(sql);
            while(rst.next()) {
                Department e = new Department();
                e.setDeptId(rst.getInt("dept_id"));
                e.setDeptName(rst.getString("dept_name"));
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
