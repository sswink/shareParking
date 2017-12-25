package com.front.dao;

import com.front.dto.ParkingWithName;
import com.front.dto.parkingLotById;
import com.front.entity.ParkingLot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingLotMapper {
    int deleteByPrimaryKey(Long parkinglotId);

    int insert(ParkingLot record);

    int insertSelective(ParkingLot record);

    ParkingLot selectByPrimaryKey(Long parkinglotId);

    int updateByPrimaryKeySelective(ParkingLot record);

    int updateByPrimaryKey(ParkingLot record);

    //停车位
    List<parkingLotById> getParkinglotByPkId (@Param("parkingId") long parkingId);
//    //所有停车位
    List<parkingLotById> getAllParkinglot ();

    //新增车位
    int insertParkingLot(@Param("parking_id")long parking_id,
                         @Param("plate_num")String plate_num,
                         @Param("user_id") long user_id);
    //删除车位
    int deleteParkingLotByParkingLotId(@Param("parkinglot_id") long parkinglot_id);
    //通过用户ID查询车位状况
    List<ParkingWithName> queryByUser_Id(@Param("user_id") long user_id);
}
