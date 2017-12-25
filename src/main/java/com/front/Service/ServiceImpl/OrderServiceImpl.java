package com.front.Service.ServiceImpl;

import com.front.Service.OrderService;
import com.front.dao.GenerateMapper;
import com.front.dao.OrderMapper;
import com.front.dto.*;
import com.front.entity.Generate;
import com.front.entity.Order;
import com.front.util.DateUtil;
import com.front.util.IdPackage;
import com.sun.javafx.binding.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GenerateMapper generateMapper;
    @Autowired
    private IdPackage idpackage;


    public Result getOrderAllDetail(long orderId,long parkingId,String orderStatus) {
        List<OrderAllDetail> orderAllDetails = orderMapper.getOrderAllDetail(orderId,parkingId,orderStatus);
        Result result = new Result(true,orderAllDetails);
        return result;
    }

    //记录入场时间
    public  Result parkingTimeBegin(long orderId){
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 格式化当前时间
        String BeginTime = s.format(new Date().getTime());
        // 接收修改行数
        int count = orderMapper.parkingTimeBegin(BeginTime,orderId);
        // 判断是否插入成功
        if(count == 1 ){
            long gId=orderMapper.getGenerateIdByOrderId(orderId);
            generateMapper.updateByPrimaryKey(gId,"3");
            return new Result(true,"插入成功");
        }
        return new Result(true,"插入失败");
    }

    //记录出场时间
    public Result parkingTimeEnd(long orderId) {
        // 接收该订单的信息
        Order order = orderMapper.selectByPrimaryKey(orderId);
        // 通过共享车位id获取共享车为信息——用于修改共享车位状态和获取共享车位时价
        Generate generate = generateMapper.selectByPrimaryKey(order.getGenerateId());
        // 创建时间格式器
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取开始时间
        String BeginTime = order.getOrderParkingBegin();
        // 获取开始时间毫秒数 (抛异常)
        long lBeginTime = 0;
        try {
            lBeginTime = s.parse(BeginTime).getTime();
        }catch (Exception e){ }
        // 获取结束时间
        Date EndTime = new Date();
        // 获取当前时间（格式化）
        String sEndTime = s.format(EndTime);
        // 获取结束时间毫秒数
        long lEndTime = EndTime.getTime();
        // 获取两个时间差
        long diff = lEndTime - lBeginTime;
        // 得到秒钟数(double)
        double dS = diff*1.0/1000;
        // 得到 总时间(时)（只保留两个小数）
        String sTime = String.format("%.2f",dS/60/60);
        // 得到对应格式的停车时间
        BigDecimal totleTime = new BigDecimal(sTime);
        // 获取共享车位时价
        double gPrice = generate.getGeneratePrice().doubleValue();
        // 算出每秒收费( hour / 60 / 60 )
        double everySPrice = gPrice/60/60;
        // 得到总价
        double price = everySPrice*dS;
        // 得到总价（只保留两位小数）
        String sPrice = String.format("%.2f",price);
        // 记录修改订单状态信息
        int count = orderMapper.parkingTimeEnd(sEndTime,totleTime,sPrice,orderId);
        // 如果成功，修改共享车位状态
        if(count == 1) {
            generateMapper.setStatusByOrderEnd(order.getGenerateId());
            return new Result(true,"修改成功");
        }
        return new Result(false,"修改失败");
    }

    public ParkingUseInfo useInfo(long order_id) {
        //查询出五个表的数据
        five or=orderMapper.queryByOrderId(order_id);
        //提取数据
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime =s.format(or.getOrderCreatetime());
        String parkLocation=or.getParking().getParkingLocation();
        String plateNum=or.getUser().getPlateNum();
        String shareBT=s.format(or.getGenerate().getGenerateShareBegin());
        String shareET=s.format(or.getGenerate().getGenerateShareEnd());
        int price=or.getGenerate().getGeneratePrice().intValue();
        String status=String.valueOf(or.getOrderStatus());
        String longtitude=or.getParking().getLongitude().toString();
        String latitude=or.getParking().getLatitude().toString();
        String parkName=or.getParking().getParkingName();
        //加密运单号
        String wayNum= idpackage.packageNum(or.getGenerate().getGenerateId());
        return new ParkingUseInfo(parkName,wayNum,shareBT,parkLocation,plateNum,shareET,price,status,latitude,longtitude,createTime);
    }

    public List<ParkingUse> queryUseList(long user_id) {
        List<ParkingUse> parkingUseList=orderMapper.queryUseList(user_id);
        List<ParkingUse> res=new ArrayList<ParkingUse>();
        for(int i=0;i<parkingUseList.size();i++){
            ParkingUse p=parkingUseList.get(i);
            BigDecimal price=p.getPrice();
            String plateNum=p.getPlateNum();
            String parkName=p.getParkName();
            String begin= DateUtil.longToDate(p.getShareBegin());
            String end=DateUtil.longToDate(p.getShareEnd());
            Long id=p.getOrderId();
            String status=p.getOrderStatus();
            ParkingUse resP=new ParkingUse(id,status,price,plateNum,parkName,begin,end);
            res.add(resP);
        }
        return res;
    }
}