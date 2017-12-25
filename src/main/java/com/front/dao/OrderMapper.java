package com.front.dao;

import com.front.dto.InsertOrder;
import com.front.dto.OrderAllDetail;
import com.front.dto.ParkingUse;
import com.front.dto.five;
import com.front.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);



    List<OrderAllDetail> getOrderAllDetail(@Param(value = "orderId") long orderId,
                                           @Param(value = "parkingId") long parkingId,
                                           @Param(value = "orderStatus")String  orderStatus );

    int parkingTimeBegin(@Param("orderParkingBegin") String orderParkingBegin,@Param("orderId") long orderId);

    int parkingTimeEnd(@Param("orderParkingEnd") String orderParkingEnd, @Param("orderParkingTotaltime") BigDecimal orderParkingTotaltime, @Param("orderPrice") String orderPrice, @Param("orderId") long orderId);

    //parkingUse.js
    List<ParkingUse> queryUseList(@Param("user_id") long user_id);
    //添加
    int insertOrder(InsertOrder insertOrder);
    //使用车位详细订单（五表查询）
    five queryByOrderId(@Param("order_id") long order_id);
    //删除订单
    int deleteOrder(@Param("order_id") long order_id);
    //改变订单状态
    int updateByPrimaryKey(@Param("order_id") long order_id,@Param("status") String status);

    //getGenerateId
    long getGenerateIdByOrderId(@Param("order_id") long order_id);
}