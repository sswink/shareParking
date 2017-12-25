package com.front.dto;

import com.front.entity.Generate;
import com.front.entity.Parking;

import java.util.List;

public class ParkingInfo extends Parking {
    List<Generate> generateList;

    public List<Generate> getGenerateList() {
        return generateList;
    }

    public void setGenerateList(List<Generate> generateList) {
        this.generateList = generateList;
    }
}