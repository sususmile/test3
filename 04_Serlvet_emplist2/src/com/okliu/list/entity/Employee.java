package com.okliu.list.entity;
/**
 * <p>Title: Employee.java</p>
 * <p>Description: 数据库操作的对象</p>
 * @author liuhaolie
 * @date 2019-02-21 10:45
 * @version 1.0
 */
public class Employee {

    private int id;
    private String name;
    private int deptId;
    private Department dept;
    
    public Department getDept() {
        return dept;
    }
    public void setDept(Department dept) {
        this.dept = dept;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    
}
