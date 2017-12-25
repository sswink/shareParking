package com.front.entity;

public class ParkingLot {
    private Long parkinglotId;

    private Long parkingId;

    private String parkinglotNum;

    private Long userId;

    public ParkingLot(Long parkinglotId, Long parkingId, String parkinglotNum, Long userId) {
        this.parkinglotId = parkinglotId;
        this.parkingId = parkingId;
        this.parkinglotNum = parkinglotNum;
        this.userId = userId;
    }

    public ParkingLot() {
        super();
    }

    public Long getParkinglotId() {
        return parkinglotId;
    }

    public void setParkinglotId(Long parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkinglotNum() {
        return parkinglotNum;
    }

    public void setParkinglotNum(String parkinglotNum) {
        this.parkinglotNum = parkinglotNum == null ? null : parkinglotNum.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}