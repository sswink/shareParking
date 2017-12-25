package com.front.dto;

import com.front.entity.ParkingLot;

public class ParkingWithName extends ParkingLot {
    private String parkingName;

    public String getParkingName() {
        return parkingName;
    }
    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }
}