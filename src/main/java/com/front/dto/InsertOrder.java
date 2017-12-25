package com.front.dto;

public class InsertOrder {
    private Long user_id;
    private Long order_id;
    private Long generate_id;
    private String status;

    public InsertOrder() {
    }

    public InsertOrder(Long user_id, Long generate_id, String status) {
        this.user_id = user_id;
        this.generate_id = generate_id;
        this.status = status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getGenerate_id() {
        return generate_id;
    }

    public void setGenerate_id(Long generate_id) {
        this.generate_id = generate_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
