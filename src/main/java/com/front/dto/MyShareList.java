package com.front.dto;


import com.front.entity.Generate;

import java.math.BigDecimal;

public class MyShareList extends Generate {
    private String plateNum;
    private String parkingName;

    private String begin;
    private String end;

    @Override
    public String getBegin() {
        return begin;
    }

    @Override
    public void setBegin(String begin) {
        this.begin = begin;
    }

    @Override
    public String getEnd() {
        return end;
    }

    @Override
    public void setEnd(String end) {
        this.end = end;
    }

    public MyShareList(Long generateId, BigDecimal generatePrice, String generateStatus, String plateNum, String parkingName, String begin, String end) {
        super(generateId, generatePrice, generateStatus);
        this.plateNum = plateNum;
        this.parkingName = parkingName;
        this.begin = begin;
        this.end = end;
    }

    public MyShareList() {
    }

    public String getParkingName() {
        return parkingName;
    }
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }
    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    @Override
    public String toString() {
        return "MyShareList{" +
                "plateNum='" + plateNum + '\'' +
                ", parkingName='" + parkingName + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                '}';
    }


}