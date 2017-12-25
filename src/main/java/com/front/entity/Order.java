package com.front.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long orderId;

    private Long userId;

    private Long generateId;

    private String orderParkingBegin;

    private String orderParkingEnd;

    private BigDecimal orderParkingTotaltime;

    private BigDecimal orderPrice;

    private String orderStatus;

    private Date orderCreatetime;

    public Order(Long orderId, String orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public Order(Long orderId, Long userId, Long generateId, String orderParkingBegin, String orderParkingEnd, BigDecimal orderParkingTotaltime, BigDecimal orderPrice, String orderStatus, Date orderCreatetime) {
        this.orderId = orderId;
        this.userId = userId;
        this.generateId = generateId;
        this.orderParkingBegin = orderParkingBegin;
        this.orderParkingEnd = orderParkingEnd;
        this.orderParkingTotaltime = orderParkingTotaltime;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.orderCreatetime = orderCreatetime;
    }

    public Order() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGenerateId() {
        return generateId;
    }

    public void setGenerateId(Long generateId) {
        this.generateId = generateId;
    }

    public String getOrderParkingBegin() {
        return orderParkingBegin;
    }

    public void setOrderParkingBegin(String orderParkingBegin) {
        this.orderParkingBegin = orderParkingBegin == null ? null : orderParkingBegin.trim();
    }

    public String getOrderParkingEnd() {
        return orderParkingEnd;
    }

    public void setOrderParkingEnd(String orderParkingEnd) {
        this.orderParkingEnd = orderParkingEnd == null ? null : orderParkingEnd.trim();
    }

    public BigDecimal getOrderParkingTotaltime() {
        return orderParkingTotaltime;
    }

    public void setOrderParkingTotaltime(BigDecimal orderParkingTotaltime) {
        this.orderParkingTotaltime = orderParkingTotaltime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }
}