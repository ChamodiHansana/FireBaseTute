package com.example.firebase;

public class Student {

    private String id;
    private String name;
    private String address;
    private Integer contNo;

    public Student() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getContNo() {
        return contNo;
    }

    public void setContNo(Integer contNo) {
        this.contNo = contNo;
    }
}
