package com.front.Service;

import com.front.dto.MyShareList;
import com.front.dto.Result;
import com.front.dto.ShareProfit;

import java.util.List;


/**
 * Created by Lin on 2017/9/29.
 */
public interface GenerateService {

    Result getGenerateAllDetail (long parkingId,String generateStatus);

    //getShare
    List<MyShareList> list(long user_id);
    //getShareProfit
    ShareProfit getProfit(long generate_id);
}
