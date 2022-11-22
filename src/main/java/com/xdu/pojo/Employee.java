package com.xdu.pojo;

public class Employee {
    private String departmentId;
    private String id;
    private String name;
    private String post;
    private String tel;
    private Integer salary;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "departmentId='" + departmentId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", post='" + post + '\'' +
                ", tel='" + tel + '\'' +
                ", salary=" + salary +
                '}';
    }
}
