package com.front.dto;

import com.front.util.ParkingUtil;

import java.util.List;

//ziheng
public class ParkingList {
    private List<ParkingUtil> ShareParkinglist;
    private List<ParkingUtil> CommonParkinglist;

    public List<ParkingUtil> getShareParkinglist() {
        return ShareParkinglist;
    }

    public void setShareParkinglist(List<ParkingUtil> shareParkinglist) {
        ShareParkinglist = shareParkinglist;
    }

    public List<ParkingUtil> getCommonParkinglist() {
        return CommonParkinglist;
    }

    public void setCommonParkinglist(List<ParkingUtil> commonParkinglist) {
        CommonParkinglist = commonParkinglist;
    }

    public ParkingList(List<ParkingUtil> shareParkinglist, List<ParkingUtil> commonParkinglist) {
        ShareParkinglist = shareParkinglist;
        CommonParkinglist = commonParkinglist;
    }
}