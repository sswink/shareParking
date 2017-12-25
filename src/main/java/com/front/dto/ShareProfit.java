package com.front.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShareProfit {
    private long generateId;
    private String parkingName;
    private Long beginShareTime;
    private String begin;
    private String end;
    private Long endShareTime;
    private BigDecimal price;  //时价
    private String status;
    private List<sharecontends> contendsList;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ShareProfit() {
    }

    public ShareProfit(String parkingName, String begin, String end, BigDecimal price, String status, List<sharecontends> contendsList) {
        this.parkingName = parkingName;
        this.begin = begin;
        this.end = end;
        this.price = price;
        this.status = status;
        this.contendsList = contendsList;
    }

    public long getGenerateId() {
        return generateId;
    }

    public void setGenerateId(long generateId) {
        this.generateId = generateId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public Long getBeginShareTime() {
        return beginShareTime;
    }

    public void setBeginShareTime(Long beginShareTime) {
        this.beginShareTime = beginShareTime;
    }

    public Long getEndShareTime() {
        return endShareTime;
    }

    public void setEndShareTime(Long endShareTime) {
        this.endShareTime = endShareTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<sharecontends> getContendsList() {
        return contendsList;
    }

    public void setContendsList(List<sharecontends> contendsList) {
        this.contendsList = contendsList;
    }
}