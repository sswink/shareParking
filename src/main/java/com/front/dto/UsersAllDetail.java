package com.front.dto;

import com.front.entity.Parking;
import com.front.entity.ParkingLot;
import com.front.entity.Users;

/**
 * Created by Lin on 2017/9/30.
 */
public class UsersAllDetail extends Users {
    private ParkingLot parkingLot;
    private Parking parking;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
