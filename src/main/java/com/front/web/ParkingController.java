package com.front.web;

import com.front.Service.AdminService;
import com.front.Service.ParkingService;
import com.front.dao.ParkingLotMapper;
import com.front.dao.ParkingMapper;
import com.front.dto.ParkingInfo;
import com.front.dto.ParkingList;
import com.front.dto.Result;
import com.front.entity.Admin;
import com.front.entity.Parking;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(value = "parking", description = "停车场")
@RequestMapping(value = "/parking")
@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingMapper parkingMapper;

    //todo 添加停车场和停车场管理员
    @RequestMapping(value = "/setParkingAndAdmin",method = RequestMethod.POST)
    public Result setParkingAndAdmin(Parking parking,Admin admin) throws Exception {
        return parkingService.setAdminAndParking(parking,admin);
    }

    //鹏
    // 通过停车场id获取停车场备注
    @RequestMapping(value = "/getRemarkById/{parkingId}")
    public Result getRemarkById(@PathVariable("parkingId") long parkingId){
        return parkingService.getRemarkById(parkingId);
    }

    // 修改停车场备注
    @RequestMapping(value = "/updataRemark/{remaker}/{parkingId}")
    public String  updataRemark(@PathVariable("remaker") String remaker,@PathVariable("parkingId")long parkingId){
        parkingService.updataRemark(remaker,parkingId);
        return "true";
    }

    // 通过停车场id获取订单
    @RequestMapping(value = "/getOrderById/{parkingId}")
    public Result getOrderById(@PathVariable("parkingId")long parkingId){
        return parkingService.getRemarkById(parkingId);
    }

    // 查看全部停车场信息
    @RequestMapping(value = "/getParkingInfo")
    public Result getParkingInfo(){
        return parkingService.getParkingInfo();
    }

    //map.js(返回一公里内的停车场)->有共享订单的一个集合 无共享订单的一个集合
    @ApiOperation(value = "一公里内的停车场",httpMethod ="GET",notes = "根据用户自身经纬度查询一公里内停车场",response = ParkingList.class)
    @RequestMapping(value = "/{nowlongitude}/{nowlatitude}/lists",method = RequestMethod.GET)
    public Result<ParkingList> list(@ApiParam(name = "nowlongitude",value = "经度",required = true)@PathVariable("nowlongitude") BigDecimal nowlongitude,
                                    @ApiParam(name = "nowlatitude",value = "纬度",required = true)@PathVariable("nowlatitude") BigDecimal nowlatitude
    ){
        try {
            ParkingList parkingStatusList=parkingService.GenerateList(nowlongitude,nowlatitude);
            return new Result<ParkingList>(true,parkingStatusList);
        }catch (Exception e){
            return new Result<ParkingList>(false,e.getMessage());
        }
    }
    //parkingLot.js (请求停车场基本信息-> 停车场信息 顺带 共享车位信息)
    @ApiOperation(value = "停车场基本信息",httpMethod ="POST",notes = "请求停车场基本信息-> 停车场信息 顺带 共享车位信息",response = ParkingInfo.class)
    @RequestMapping(method = RequestMethod.POST,value="/{parking_id}/querypg")
    public Result<ParkingInfo> queryParkingWithGen(@ApiParam(name = "parking_id",value = "停车场ID",required = true)@PathVariable("parking_id") long parking_id){
        try{
            ParkingInfo parkinggener= parkingService.queryParkingInfo(parking_id);
            return new Result<ParkingInfo>(true,parkinggener);
        }catch (Exception e){
            return new Result<ParkingInfo>(false,e.getMessage());
        }
    }

    //根据id获取停车场信息
    @RequestMapping(value = "/getParkingById/{parkingId}")
    @ResponseBody
    public Parking getParkingById(@PathVariable("parkingId") long parkingId){
        return parkingService.getPakingById(parkingId);
    }

    //todo
    @RequestMapping(value = "/updateParking",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> updateParking(String parkingName, Parking parking){
        parkingService.updateParking(parking);
        return new Result<Boolean>(true,"修改成功");
    }
}