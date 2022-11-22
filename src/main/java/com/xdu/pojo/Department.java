package com.xdu.pojo;

public class Department {
    private String mallId;
    private String id;
    private String name;
    private String addr;
    private String tel;

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Department{" +
                "mallId='" + mallId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
