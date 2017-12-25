package com.front.dto;

import com.front.entity.Generate;
import com.front.entity.Order;
import com.front.entity.ParkingLot;
import com.front.entity.Users;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 鸿鹄 on 2017/9/28.
 */
public class GenerateOrder extends Generate{
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
