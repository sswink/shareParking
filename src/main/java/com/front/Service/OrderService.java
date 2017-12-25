package com.front.Service;

import com.front.dto.OrderAllDetail;
import com.front.dto.ParkingUse;
import com.front.dto.ParkingUseInfo;
import com.front.dto.Result;

import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
public interface OrderService {
    Result getOrderAllDetail(long orderId,long parkingId,String orderStatus);

    //记录路场时间
    Result parkingTimeBegin(long orderId);
    //记录出场时间
    Result parkingTimeEnd(long orderId);

    //使用订单详细信息
    ParkingUseInfo useInfo(long order_id);

    //使用订单信息
    List<ParkingUse> queryUseList(long user_id);
}