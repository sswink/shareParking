package com.front.Service.ServiceImpl;

import com.front.Service.GenerateService;
import com.front.dao.GenerateMapper;
import com.front.dto.*;
import com.front.util.DateUtil;
import com.front.util.IdPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
@Service
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    private GenerateMapper generateMapper;

    public Result getGenerateAllDetail (long parkingId,String generateStatus){
        List<GenerateAllDetail> generateAllDetails = generateMapper.getGenerateAllDetail( parkingId, generateStatus);
        Result result = new Result(true,generateAllDetails);
        return result;
    }

    //共享收益
    public ShareProfit getProfit(long generate_id) {
        ShareProfit ob=generateMapper.generateProfit(generate_id);
        SimpleDateFormat g=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parkName=ob.getParkingName();
//        String shareBeginT=g.format(ob.getBeginShareTime().getTime());
//        String shareEndT=g.format(ob.getEndShareTime().getTime());
        String  status=String.valueOf(ob.getStatus());
        BigDecimal timeprice=ob.getPrice();
        List<sharecontends> newcontends=new ArrayList<sharecontends>();
        for(int i=0;i<ob.getContendsList().size();i++){
            sharecontends contends=ob.getContendsList().get(i);
            String waynum= IdPackage.packageNum(contends.getOrderId());
            BigDecimal Allprice=contends.getAllPrice();
            String beginParkingT=contends.getParkingBeginTime();
            String endParkingT=contends.getParkingEndTime();
            if(beginParkingT.equals("2000-01-01 00:00:00")||beginParkingT==null){
                beginParkingT="未登记入场时间";
                endParkingT="未登记出场时间";
            }else if(endParkingT.equals("2000-01-01 00:00:00")||endParkingT==null){
                endParkingT="未登记出场时间";
            }
            sharecontends c=new sharecontends(waynum,beginParkingT,endParkingT,Allprice);
            newcontends.add(c);
        }
        return new ShareProfit(parkName,DateUtil.longToDate(ob.getBeginShareTime()),DateUtil.longToDate(ob.getEndShareTime()),timeprice,status,newcontends);
    }

    //共享列表
    public List<MyShareList> list(long user_id) {
        List<MyShareList> lists=generateMapper.queryById(user_id);
        List<MyShareList> res=new ArrayList<MyShareList>();
        for(int i=0;i<lists.size();i++){
            MyShareList myShareList=lists.get(i);
            Long generate_id=myShareList.getGenerateId();
            BigDecimal price=myShareList.getGeneratePrice();
            String begin= DateUtil.longToDate(myShareList.getGenerateShareBegin());
            String end=DateUtil.longToDate(myShareList.getGenerateShareEnd());
            String parkingName=myShareList.getParkingName();
            String status=myShareList.getGenerateStatus();
            String plateNum=myShareList.getPlateNum();
            MyShareList my=new MyShareList(generate_id,price,status,plateNum,parkingName,begin,end);
            res.add(my);
        }
        return res;
    }
}
