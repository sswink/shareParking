package com.front.Service;

import com.front.dao.GenerateMapper;
import com.front.dao.OrderMapper;
import com.front.dao.ParkingLotMapper;
import com.front.dao.ParkingMapper;
import com.front.dto.*;
import com.front.util.DateUtil;
import com.front.util.ParkingUtil;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class GenerateServiceTest {
    @Test
    public void getProfit() throws Exception {
    }

    @Autowired
    private GenerateService generateService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GenerateMapper generateMapper;

    @Test
    public void list() throws Exception {
        List<MyShareList> list = generateService.list(17);
        System.out.println(list);
    }

    //停车场信息
    @Test
    public void queryParkingInfo() throws Exception {
//        ParkingInfo parkinggener= parkingService.queryParkingInfo(10);
//        System.out.println(parkinggener);
    }
    //新增共享订单
    @Test
    public void insertGenerate() throws Exception {
        generateMapper.insertGenerate(20, DateUtil.dateToLong("2017-11-08 13:09"), DateUtil.dateToLong("2017-11-10 15:36"), 30, "1");
        generateMapper.insertGenerate(20, DateUtil.dateToLong("2017-11-08 13:09"), DateUtil.dateToLong("2017-11-10 15:36"), 30, "1");
        generateMapper.insertGenerate(20, DateUtil.dateToLong("2017-11-08 13:09"), DateUtil.dateToLong("2017-11-10 15:36"), 30, "0");
        generateMapper.insertGenerate(20, DateUtil.dateToLong("2017-11-08 13:09"), DateUtil.dateToLong("2017-11-10 15:36"), 30, "1");
    }
    //新增Order
    @Test
    public void insertOrder() throws Exception {
        InsertOrder insertOrder=new InsertOrder(new Long(17),new Long(38),"0");
        //int s=orderMapper.insertOrder(17,36,"0");
         int s=orderMapper.insertOrder(insertOrder);
        System.out.println(insertOrder.getOrder_id());
    }
    //经纬度
    @Test
    public void generateList() throws Exception {
        ParkingList parkingStatusList=parkingService.GenerateList(new BigDecimal("113.3211193178711"),new BigDecimal("23.17243924629782"));
        System.out.println(parkingStatusList);
    }
    @Test
    public void gent() throws Exception {
        List<ParkingUtil> all =parkingMapper.queryBycondition(new BigDecimal("113.3211193178711"),new BigDecimal("23.17243924629782"));
        System.out.println(all);
    }
    @Test
    public void count() throws Exception {
        Integer s=parkingMapper.count(8,5);
        System.out.println(s);
    }
    @Test
    public void deleteParkingLotByParkingLotId() throws Exception {
        int s=parkingLotMapper.deleteParkingLotByParkingLotId(12);
        System.out.println(s);
    }

    @Test
    public void s() throws  Exception{
//        ShareProfit getProfit=generateService.getProfit(7);
//        System.out.println(getProfit);
    }
}