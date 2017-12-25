package com.front.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Generate {
    private Long generateId;

    private Long parkinglotId;

    private Long generateShareBegin;

    private Long generateShareEnd;

    private BigDecimal generatePrice;

    private String generateStatus;

    private String begin;
    private String end;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Generate(Long generateId, Long parkinglotId, BigDecimal generatePrice, String generateStatus, String begin, String end) {
        this.generateId = generateId;
        this.parkinglotId = parkinglotId;
        this.generatePrice = generatePrice;
        this.generateStatus = generateStatus;
        this.begin = begin;
        this.end = end;
    }
//
//    public Generate(Long generateId, Long parkinglotId, Long generateShareBegin, Long generateShareEnd, BigDecimal generatePrice, String generateStatus) {
//        this.generateId = generateId;
//        this.parkinglotId = parkinglotId;
//        this.generateShareBegin = generateShareBegin;
//        this.generateShareEnd = generateShareEnd;
//        this.generatePrice = generatePrice;
//        this.generateStatus = generateStatus;
//    }

    public Generate(Long generateId, BigDecimal generatePrice, String generateStatus) {
        this.generateId = generateId;
        this.generatePrice = generatePrice;
        this.generateStatus = generateStatus;
    }

    public Generate() {

    }

    public Long getGenerateId() {
        return generateId;
    }

    public void setGenerateId(Long generateId) {
        this.generateId = generateId;
    }

    public Long getParkinglotId() {
        return parkinglotId;
    }

    public void setParkinglotId(Long parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public Long getGenerateShareBegin() {
        return generateShareBegin;
    }

    public void setGenerateShareBegin(Long generateShareBegin) {
        this.generateShareBegin = generateShareBegin;
    }

    public Long getGenerateShareEnd() {
        return generateShareEnd;
    }

    public void setGenerateShareEnd(Long generateShareEnd) {
        this.generateShareEnd = generateShareEnd;
    }

    public BigDecimal getGeneratePrice() {
        return generatePrice;
    }

    public void setGeneratePrice(BigDecimal generatePrice) {
        this.generatePrice = generatePrice;
    }

    public String getGenerateStatus() {
        return generateStatus;
    }

    public void setGenerateStatus(String generateStatus) {
        this.generateStatus = generateStatus == null ? null : generateStatus.trim();
    }
}