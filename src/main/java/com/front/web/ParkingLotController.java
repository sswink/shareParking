package com.front.web;

import com.front.Service.ParkingLotService;
import com.front.dao.ParkingLotMapper;
import com.front.dao.ParkingMapper;
import com.front.dao.UsersMapper;
import com.front.dto.ParkingWithName;
import com.front.dto.Result;
import com.front.entity.ParkingLot;
import com.front.entity.Users;
import com.front.util.IdPackage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 鸿鹄 on 2017/9/27.
 */
@RequestMapping(value = "/parkinglot")
@RestController
public class ParkingLotController {


    @Autowired
    private IdPackage idpackage;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    @Autowired
    private ParkingLotService parkingLotService;
    @Autowired
    private ParkingMapper parkingMapper;
    // 通过停车场id找到停车位（列表）
    @RequestMapping(value = "/getParkinglotByPkId/{parkingId}")
    public Result getParkinglotByPkId(@PathVariable(value = "parkingId") long parkingId){
        return parkingLotService.getParkinglotByPkId(parkingId);

    }

    // 查找全部停车位（列表）
    @RequestMapping(value = "/getAllParkinglot")
    public Result getAllParkinglot(){
        return parkingLotService.getAllParkinglot();
    }

    //parkingLot.js(添加停车位事件处理) todo
    @ApiOperation(value = "新增车位",httpMethod ="POST",notes = "用户新增车位",response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST,value = "/{parking_id}/{rds}/insertParkingLot")
    public Result<Boolean> insertParkingLot(@ApiParam(name = "parking_id",value = "停车场ID",required = true)@PathVariable("parking_id")long parking_id,
                                            @ApiParam(name = "rds",value = "3rds_Session",required = true)@PathVariable("rds")String rds){
        try{
            String openId=idpackage.getOpenId(rds);
            Users user=usersMapper.queryBywxOpenId(openId);
            long user_id=user.getUserId();
            String plate_num=user.getPlateNum();
            Integer count=parkingMapper.count(parking_id,user_id);
            if(count>=1){
                return new Result<Boolean>(false,"停车位已存在");
            }else {
                parkingLotMapper.insertParkingLot(parking_id,plate_num,user_id);
                return new Result<Boolean>(true,true);
            }

        }catch (Exception e){//停车位已存在
            return new Result<Boolean>(false,e.getMessage());
        }
    }
    //parkingSpace.js(删除车位)
    @ApiOperation(value = "删除车位",httpMethod ="DELETE",notes = "根据某个车位刪除",response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST,value = "/{parkinglot_id}/deleteLot")
    public Result<Boolean> deleteParkingLot(@ApiParam(name = "parkinglot_id",value ="车位ID",required = true)@PathVariable("parkinglot_id") long parkinglot_id){
        try{
            parkingLotMapper.deleteParkingLotByParkingLotId(parkinglot_id);
            return new Result<Boolean>(true,true);
        }catch (Exception e){
            return new Result<Boolean>(false,e.getMessage());
        }
    }
    //parkingSpace.js(加载停车位数据)
    @ApiOperation(value = "我的停车位列表",httpMethod ="POST",notes = "列出用户的停车位",response = ParkingLot.class)
    @RequestMapping(method = RequestMethod.POST,value = "/{rds}/myparkingLot")
    public Result<List<ParkingWithName>> parkingLotByUser(@ApiParam(required = true,value ="rds",name = "rds")@PathVariable("rds") String rds){
        try{
            System.out.println(rds);
            String openId=idpackage.getOpenId(rds);
            System.out.println(openId);
            Users user=usersMapper.queryBywxOpenId(openId);
            long user_id=user.getUserId();
            List<ParkingWithName> parkinglotList=parkingLotMapper.queryByUser_Id(user_id);
            return new Result<List<ParkingWithName>>(true,parkinglotList);
        }catch (Exception e){
            return new Result<List<ParkingWithName>>(false,e.getMessage());
        }
    }
}

