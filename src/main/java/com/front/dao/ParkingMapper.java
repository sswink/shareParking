package com.front.dao;

import com.front.dto.ParkingInfo;
import com.front.dto.ParkingOrders;
import com.front.dto.ParkingToShare;
import com.front.entity.Parking;
import com.front.util.ParkingUtil;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ParkingMapper {
    int deleteByPrimaryKey(Long parkingId);

    int insert(Parking record);

    int insertSelective(Parking record);

    Parking selectByPrimaryKey(Long parkingId);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);

    // 鹏
    public  void updataRemark(@Param("remaker") String remaker, @Param("parkingId") long parkingId);
//    public String getRemarkById( @Param("parking_id") long parking_id);

//    停车场订单
    public List<ParkingOrders> getOrderById(@Param("parkingId") long parkingId);
    public List<Parking> getParkingInfo();

    // 选择最新的停车场id
    long getNewParkingId();

    //返回3公里内停车场列表
    List<ParkingUtil> queryBycondition(@Param("nowlongitude") BigDecimal nowlongitude,
                                      @Param("nowlatitude") BigDecimal nowlatitude
    );
    //根据停车场ID顺带其停车场内的共享表
    ParkingInfo queryParkingInfo (@Param("parking_id") long parking_id);
    //new 通过plid查询
    ParkingToShare queryByplid(@Param("parkinglot_id") long parkinglot_id);
    //判断该停车场是否有自己车位
    Integer count(@Param("parkingId") long parkingId,@Param("userId") long userId);
}