package com.front.dto;

import com.front.entity.*;

/**
 * Created by 鸿鹄 on 2017/9/28.
 */
public class ParkingOrders extends Parking {
    private Users users;
    private Generate generate;
    private Order orders;
    private ParkingLot parkinglot;
    @Override
    public String toString() {
        return super.toString()+
                "PakingOrders{" +
                "users=" + users +
                ", generate=" + generate +
                ", orders=" + orders +
                ", parkinglot=" + parkinglot +
                '}';
    }
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

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public ParkingLot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
    }
}
