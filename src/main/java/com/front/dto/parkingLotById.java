package com.front.dto;

import com.front.entity.Parking;
import com.front.entity.ParkingLot;
import com.front.entity.Users;

/**
 * Created by 鸿鹄 on 2017/9/28.
 */
public class parkingLotById extends ParkingLot {
   private Parking parking;
   private Users users;

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
