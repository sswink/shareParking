package com.front.dto;

public class ParkingUseInfo {
    private String parkingName;
    private String wayNum;  //运单编号
    private String shareBeginT;
    private String parkingLocation;
    private String plateNum;
    private String endShareTime;
    private int price;  //时价
    private String status;
    private String latitude;
    private String longitude;
    private String createTime;

    public ParkingUseInfo(String parkingName, String wayNum, String shareBeginT, String parkingLocation, String plateNum, String endShareTime, int price, String status, String latitude, String longitude, String createTime) {
        this.parkingName = parkingName;
        this.wayNum = wayNum;
        this.shareBeginT = shareBeginT;
        this.parkingLocation = parkingLocation;
        this.plateNum = plateNum;
        this.endShareTime = endShareTime;
        this.price = price;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = createTime;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getWayNum() {
        return wayNum;
    }

    public void setWayNum(String wayNum) {
        this.wayNum = wayNum;
    }

    public String getShareBeginT() {
        return shareBeginT;
    }

    public void setShareBeginT(String shareBeginT) {
        this.shareBeginT = shareBeginT;
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getEndShareTime() {
        return endShareTime;
    }

    public void setEndShareTime(String endShareTime) {
        this.endShareTime = endShareTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}