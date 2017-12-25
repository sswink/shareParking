package com.front.Service;

import com.front.dto.ParkingInfo;
import com.front.dto.ParkingList;
import com.front.dto.ParkingOrders;
import com.front.dto.Result;
import com.front.entity.Admin;
import com.front.entity.Parking;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ParkingService {

    Result getRemarkById(long parkingId);

    void updataRemark(String remaker, long parkingId);

    Result getOrderById(long parkingId);

    Result getParkingInfo();

    Result setAdminAndParking(Parking parking,Admin admin);

    //返回三公里内范围停车场
    ParkingList GenerateList(BigDecimal nowlongitude, BigDecimal nowlatitude);

    //亮
    Parking getPakingById(long parkingId);

    void updateParking(Parking parking);

    //停车场基本信息
    ParkingInfo queryParkingInfo(long parking_id);
}