package com.front.web;

import com.front.Service.GenerateService;
import com.front.dao.GenerateMapper;
import com.front.dao.ParkingMapper;
import com.front.dao.UsersMapper;
import com.front.dto.MyShareList;
import com.front.dto.ParkingToShare;
import com.front.dto.Result;
import com.front.dto.ShareProfit;
import com.front.entity.Generate;
import com.front.entity.Users;
import com.front.util.DateUtil;
import com.front.util.IdPackage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lin on 2017/9/29.
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;
    @Autowired
    private IdPackage idpackage;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private GenerateMapper generateMapper;
    @Autowired
    private ParkingMapper parkingMapper;

    // 通过订单状态查看全部该状态的订单
    //-  升级版：通过停车场id和状态查询该停车场的共享车位信息
    // 说明： 如果parkingId 为-1 ： 查询所有的停车场   否则指查询对应的停车场下的共享车位
    //        如果generateStatus 为-1 ： 查询所有的共享车位信息   否则只查询对应状态的共享车位
    @RequestMapping(value = "/getGenerateAllDetail/{parkingId}/{generateStatus}")
    public Result getGenerateAllDetail(@PathVariable(value = "parkingId")long parkingId,
                                       @PathVariable(value = "generateStatus")String generateStatus){
        return generateService.getGenerateAllDetail(parkingId,generateStatus);
    }

    //parkingShare.js 共享停车位管理(监听页面加载)
    @ApiOperation(value = "parkingShare.js", httpMethod = "POST", notes = "parkingShare.js 共享停车位管理(监听页面加载)", response = ShareProfit.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{rds}/GenerateShareList")
    public Result<List<MyShareList>> GenerateShareList(@ApiParam(name = "rds",value = "加密值" ,required = true) @PathVariable("rds") String rds) {
        String wxopen_id = idpackage.getOpenId(rds);
        Users user = usersMapper.queryBywxOpenId(wxopen_id);
        try {
            List<MyShareList> list = generateService.list(user.getUserId());
            return new Result<List<MyShareList>>(true, list);
        } catch (Exception e) {
            return new Result<List<MyShareList>>(false, e.getMessage());
        }
    }

    //parkingShareInfo.js(共享收益)
    @ApiOperation(value = "共享车位管理详情-共享收益", httpMethod = "POST", notes = "共享停车位列表管理详情 状态(0:正在共享 1：已预约 2：已完成 3：已结束 4：已取消)", response = ShareProfit.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{generate_id}/shareProfit")
    public Result<ShareProfit> profit(@ApiParam(name = "generate_id", value = "共享ID", required = true) @PathVariable("generate_id") long generate_id) {
        try {
            ShareProfit generateContends = generateService.getProfit(generate_id);
            return new Result<ShareProfit>(true, generateContends);
        } catch (Exception e) {
            return new Result<ShareProfit>(false, e.getMessage());
        }
    }

    //share.js (生成共享订单)
    @ApiOperation(value = "生成共享订单", httpMethod = "POST", notes = "生成共享订单 状态：正在共享 status(0:正在共享 1：已预约 2：已完成 3：已结束 4：已取消)", response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{parkinglot_id}/{share_begin}/{share_end}/{price}/insertShare")
    public Result<Boolean> insertGenerate(
            @ApiParam(name = "parkinglot_id", value = "车位ID", required = true) @PathVariable("parkinglot_id") long parkinglot_id,
            @ApiParam(name = "share_begin", value = "共享开始时间", required = true) @PathVariable("share_begin") String share_begin,
            @ApiParam(name = "share_end", value = "共享结束时间", required = true) @PathVariable("share_end") String share_end,
            @ApiParam(name = "price", value = "时价", required = true) @PathVariable("price") int price
    ) {
        try {
            long begin=DateUtil.dateToLong(share_begin);
            long end=DateUtil.dateToLong(share_end);
            generateMapper.insertGenerate(parkinglot_id, begin, end, price, "0");
            return new Result<Boolean>(true, true);
        } catch(Exception e) {
            return new Result<Boolean>(false, e.getMessage());
        }
    }

    //share.js（通过车位ID页面加载）
    @ApiOperation(value = "share.js", httpMethod = "POST", notes = "share.js（通过车位ID页面加载）", response = ParkingToShare.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{parkinglot_id}/querybyplid")
    public Result<ParkingToShare> queryByplid(@ApiParam(name = "parkinglot_id",value = "停车位ID",required = true)@PathVariable("parkinglot_id") long parkinglot_id) {
        try {
            ParkingToShare parkingshare = parkingMapper.queryByplid(parkinglot_id);
            return new Result<ParkingToShare>(true, parkingshare);
        } catch (Exception e) {
            return new Result<ParkingToShare>(false, e.getMessage());
        }
    }
}