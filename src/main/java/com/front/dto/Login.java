package com.front.dto;

public class Login {
    private String wxName;
    private String userName;
    private String sex;
    private String phoneNum;
    private String plateNum;
    private String rds;

    public Login(String rds) {
        this.rds = rds;
    }

    public Login(String wxName, String userName, String sex, String phoneNum, String plateNum, String rds) {
        this.wxName = wxName;
        this.userName = userName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.plateNum = plateNum;
        this.rds = rds;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getRds() {
        return rds;
    }

    public void setRds(String rds) {
        this.rds = rds;
    }
}