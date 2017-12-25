package com.front.web;

import com.front.Service.GenerateService;
import com.front.Service.OrderService;
import com.front.dao.GenerateMapper;
import com.front.dao.OrderMapper;
import com.front.dao.UsersMapper;
import com.front.dto.InsertOrder;
import com.front.dto.ParkingUse;
import com.front.dto.ParkingUseInfo;
import com.front.dto.Result;
import com.front.entity.Users;
import com.front.util.IdPackage;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private IdPackage idpackage;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GenerateMapper generateMapper;

    // 通过订单id查询该订单对应的所有信息
    // 通过停车场id查询停车场内所有的订单
    // 通过订单状态查看该状态下的订单的信息
    @RequestMapping(value = "getOrderAllDetail/{orderId}/{parkingId}/{orderStatus}")
    public Result getOrderAllDetail(@PathVariable(value = "orderId") long orderId,
                                    @PathVariable(value = "parkingId") long parkingId,
                                    @PathVariable(value = "orderStatus") String orderStatus){
        return orderService.getOrderAllDetail(orderId,parkingId,orderStatus);
    }


    //停车场管理记录入场时间
    @RequestMapping(value = "/updateParkingBegin/{orderId}",method = RequestMethod.GET )
    public Result pkTimeBegin(@PathVariable(value = "orderId") long orderId) {
        return orderService.parkingTimeBegin( orderId );
    }

    //停车场管理记录结束时间
    @RequestMapping(value = "/updateParkingEnd/{orderId}",method = RequestMethod.GET)
    public Result pkTimeEnd( @PathVariable("orderId") long orderId ) {
        return orderService.parkingTimeEnd(orderId);
    }


    //parkingUse.js(监听页面加载)
    @ApiOperation(value = "parkingUse.js",httpMethod ="POST",notes = "parkingUse.js(监听页面加载)",response = ParkingUse.class)
    @RequestMapping(method = RequestMethod.POST,value="/{rds}/newQueryParkingList")
    public Result<List<ParkingUse>> newQueryParkingList(@ApiParam(name = "rds",value = "加密值" ,required = true)@PathVariable("rds")String  rds){
        String wxopen_id=idpackage.getOpenId(rds);
        Users user=usersMapper.queryBywxOpenId(wxopen_id);
        try{
            return new Result<List<ParkingUse>>(true,orderService.queryUseList(user.getUserId()));
        }catch (Exception e){
            return new Result<List<ParkingUse>>(false,e.getMessage());
        }
    }

    //parkinglot.js(使用停车位)
    @ApiOperation(value = "插入租赁订单",httpMethod ="POST",notes = "插入租赁订单并更新共享订单(status),状态(0:正在共享 1：已预约 2：已完成 3：已结束 4：已取消)",response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST,value = "/{rds}/{generate_id}/insertOrder")
    public Result<Long> insertOrder(
            @ApiParam(name = "rds",value = "3rd_SesionId" ,required = true)@PathVariable("rds") String rds,
            @ApiParam(name = "generate_id",value = "共享ID" ,required = true)@PathVariable("generate_id") long generate_id
    ){
        try{
            String openID=idpackage.getOpenId(rds);
            Users user=usersMapper.queryBywxOpenId(openID);
            long user_id=user.getUserId();
            InsertOrder insertOrder=new InsertOrder(user_id,generate_id,"0");
            //插入order表
            orderMapper.insertOrder(insertOrder);
            //更新generate状态
            generateMapper.updateByPrimaryKey(generate_id,"2");
            //返回新增键值
            return new Result<Long>(true,insertOrder.getOrder_id());
        }catch (Exception e){
            return new Result<Long>(false,e.getMessage());
        }
    }

    //parkingUseInfo.js 车位使用详情(监听页面加载)
    @ApiOperation(value = "租赁车位管理详情-车位信息",httpMethod ="POST",notes = "车位使用列表管理详情",response = ParkingUseInfo.class)
    @RequestMapping(method = RequestMethod.POST,value = "/{order_id}/orderDetail")
    public Result<ParkingUseInfo> orderDetail(
            @ApiParam(name = "order_id",value = "租赁ID")@PathVariable("order_id") long order_id
    ){
        try{
            ParkingUseInfo parkingUseInfo=orderService.useInfo(order_id);
            return new Result<ParkingUseInfo>(true,parkingUseInfo);
        }catch (Exception e){
            return new Result<ParkingUseInfo>(false,e.getMessage());
        }
    }

    //parkingUseInfo.js(删除order订单)取消订单
    @ApiOperation(value = "删除order订单",httpMethod ="POST",notes = "删除order订单",response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST,value="/{order_id}/deleteOrder")
    public Result<Boolean> deleteOrder(@PathVariable("order_id")long  order_id){
        try{
            orderMapper.updateByPrimaryKey(order_id,"3");
            return new Result<Boolean>(true,true);
        }catch (Exception e){
            return new Result<Boolean>(false,e.getMessage());
        }
    }
}