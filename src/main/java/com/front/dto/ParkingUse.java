package com.front.dto;

import com.front.entity.Order;

import java.math.BigDecimal;
import java.util.Date;

public class ParkingUse extends Order {
    private BigDecimal price;
    private String plateNum;
    private String parkName;
    private long shareBegin;
    private long shareEnd;

    private String begin;
    private String end;

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ParkingUse() {
    }

    public ParkingUse(Long orderId, String orderStatus, BigDecimal price, String plateNum, String parkName, String begin, String end) {
        super(orderId, orderStatus);
        this.price = price;
        this.plateNum = plateNum;
        this.parkName = parkName;
        this.begin = begin;
        this.end = end;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public long getShareBegin() {
        return shareBegin;
    }

    public void setShareBegin(long shareBegin) {
        this.shareBegin = shareBegin;
    }

    public long getShareEnd() {
        return shareEnd;
    }

    public void setShareEnd(long shareEnd) {
        this.shareEnd = shareEnd;
    }

    @Override
    public String toString() {
        return "ParkingUse{" +
                "price=" + price +
                ", plateNum='" + plateNum + '\'' +
                ", parkName='" + parkName + '\'' +
                ", shareBegin=" + shareBegin +
                ", shareEnd=" + shareEnd +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}