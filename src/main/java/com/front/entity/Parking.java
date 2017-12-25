package com.front.entity;

import java.math.BigDecimal;

public class Parking {
    private Long parkingId;

    private String parkingName;

    private String parkingLocation;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String remaker;

    public Parking(Long parkingId, String parkingName, String parkingLocation, BigDecimal longitude, BigDecimal latitude, String remaker) {
        this.parkingId = parkingId;
        this.parkingName = parkingName;
        this.parkingLocation = parkingLocation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.remaker = remaker;
    }

    public Parking() {
        super();
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName == null ? null : parkingName.trim();
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation == null ? null : parkingLocation.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getRemaker() {
        return remaker;
    }

    public void setRemaker(String remaker) {
        this.remaker = remaker == null ? null : remaker.trim();
    }
}