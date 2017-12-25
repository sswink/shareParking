package com.front.dto;

import com.front.entity.*;

/**
 * Created by Lin on 2017/9/29.
 */
public class GenerateAllDetail extends Generate {
    private ParkingLot parkingLot;
    private Users users;
    private Parking parking;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
