package com.front.entity;

import java.util.Date;

public class Users {
    private Long userId;

    private String wxId;

    private String wxName;

    private String userName;

    private String sex;

    private String phoneNum;

    private String plateNum;

    private Date registTime;

    private Date lastloginTime;

    public Users(Long userId, String wxId, String wxName, String userName, String sex, String phoneNum, String plateNum, Date registTime, Date lastloginTime) {
        this.userId = userId;
        this.wxId = wxId;
        this.wxName = wxName;
        this.userName = userName;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.plateNum = plateNum;
        this.registTime = registTime;
        this.lastloginTime = lastloginTime;
    }

    public Users() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum == null ? null : plateNum.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public Date getLastloginTime() {
        return lastloginTime;
    }

    public void setLastloginTime(Date lastloginTime) {
        this.lastloginTime = lastloginTime;
    }
}