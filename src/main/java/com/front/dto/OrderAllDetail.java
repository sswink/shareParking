package com.front.dto;

import com.front.dao.GenerateMapper;
import com.front.entity.*;

/**
 * Created by Lin on 2017/9/29.
 */
public class OrderAllDetail extends Order {

    private Users users;
    private Generate generate;
    private ParkingLot parkingLot;
    private Parking parking;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Generate getGenerate() {
        return generate;
    }

    public void setGenerate(Generate generate) {
        this.generate = generate;
    }

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
