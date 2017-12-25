package com.front.Service.ServiceImpl;

import com.front.Service.ParkingLotService;
import com.front.dao.ParkingLotMapper;
import com.front.dto.Result;
import com.front.dto.parkingLotById;
import com.front.entity.ParkingLot;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    public Result getParkinglotByPkId(long parkingId) {
        List<parkingLotById> parkingLots = parkingLotMapper.getParkinglotByPkId(parkingId);
        Result result = new Result(true,parkingLots);
        return result;
    }

    public Result getAllParkinglot( ) {
        List<parkingLotById> parkingLots = parkingLotMapper.getAllParkinglot();
        return new Result(true,parkingLots);
    }
}
