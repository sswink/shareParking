package com.front.util;

import java.math.BigDecimal;

public class ParkingUtil {
    private long parkingId;
    private String parkingName;
    private String parkingLocation;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String remaker;
    private String status;


    public long getParkingId() {
        return parkingId;
    }

    public void setParkingId(long parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(String parkingLocation) {
        this.parkingLocation = parkingLocation;
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
        this.remaker = remaker;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o){
            return true;
        }
        if(o instanceof ParkingUtil){
            ParkingUtil hasPakinglot=(ParkingUtil)o;
            if(this.getParkingId()==hasPakinglot.getParkingId()&&this.getParkingName().equals(hasPakinglot.getParkingName())&&this.getStatus().equals(hasPakinglot.getStatus())){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int)this.getParkingId()+this.getParkingName().hashCode();
    }
}