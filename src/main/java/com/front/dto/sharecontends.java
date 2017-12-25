package com.front.dto;

import java.math.BigDecimal;

public class sharecontends {
    private long orderId;  //运单编号
    private String parkingBeginTime;
    private String parkingEndTime;
    private BigDecimal allPrice;
    String wayNum;

    public sharecontends() {
    }

    public sharecontends(String wayNum, String parkingBeginTime, String parkingEndTime, BigDecimal allPrice) {
        this.wayNum = wayNum;
        this.parkingBeginTime = parkingBeginTime;
        this.parkingEndTime = parkingEndTime;
        this.allPrice = allPrice;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getParkingBeginTime() {
        return parkingBeginTime;
    }

    public void setParkingBeginTime(String parkingBeginTime) {
        this.parkingBeginTime = parkingBeginTime;
    }

    public String getParkingEndTime() {
        return parkingEndTime;
    }

    public void setParkingEndTime(String parkingEndTime) {
        this.parkingEndTime = parkingEndTime;
    }

    public BigDecimal getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    public String getWayNum() {
        return wayNum;
    }

    public void setWayNum(String wayNum) {
        this.wayNum = wayNum;
    }
}