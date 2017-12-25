package com.front.Service.ServiceImpl;

import com.front.Service.ParkingService;
import com.front.dao.AdminMapper;
import com.front.dao.ParkingMapper;
import com.front.dto.ParkingInfo;
import com.front.dto.ParkingList;
import com.front.dto.ParkingOrders;
import com.front.dto.Result;
import com.front.entity.Admin;
import com.front.entity.Generate;
import com.front.entity.Parking;
import com.front.util.DateUtil;
import com.front.util.ParkingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;
    @Autowired
    private AdminMapper adminMapper;

    public Result getRemarkById(long parkingId){
        Parking parking = parkingMapper.selectByPrimaryKey(parkingId);
        return new Result(true,parking.getRemaker());
    }

    public void updataRemark(String remaker, long parkingId){
        parkingMapper.updataRemark(remaker,parkingId);
    }

    public Result getOrderById(long parkingId){
        List<ParkingOrders> parkingOrders = parkingMapper.getOrderById(parkingId);
        return new Result(true,parkingOrders);
    }

    public Result getParkingInfo(){
        return new Result(true,parkingMapper.getParkingInfo());
    }

    public Result setAdminAndParking(Parking parking,Admin admin) {
        // 通过停车场管理员的名字查询停车场管理员是否存在
        Admin ad = adminMapper.getAdminByName(admin.getAdminName());
        // 如果不存在，ad为空
        if(ad == null){
            int pCount = parkingMapper.insertSelective(parking);
            // 如果pCount = 1 ，证明插入成功
            if(pCount == 1){
                // 获取最新id
                long newParkingId = parkingMapper.getNewParkingId();
                // 将parkingId 添加到 admin中
                admin.setParkingId(newParkingId);
                // 插入整个admin
                int aCount = adminMapper.insertSelective(admin);
                // 如果插入成功
                if(aCount == 1 ){
                    return new Result(true,"插入成功");
                }
                return new Result(true,"添加管理员失败");
            }
            return new Result(true,"添车位失败");
        }
        return new Result(true,"管理员昵称已存在");
    }

    public ParkingList GenerateList(BigDecimal nowlongitude, BigDecimal nowlatitude) {
        HashMap<Long,ParkingUtil> shareMap=new HashMap<Long, ParkingUtil>();
        HashMap<Long,ParkingUtil> commonMap=new HashMap<Long, ParkingUtil>();
        List<ParkingUtil> all =parkingMapper.queryBycondition(nowlongitude,nowlatitude);
        for(ParkingUtil hasPakinglot:all){
            if(hasPakinglot.getStatus()!=null) {
                if (hasPakinglot.getStatus().equals("0")&&!shareMap.containsKey(hasPakinglot.getParkingId())) {
                    shareMap.put(hasPakinglot.getParkingId(), hasPakinglot);
                    if(commonMap.containsKey(hasPakinglot.getParkingId())){
                        commonMap.remove(hasPakinglot.getParkingId());
                    }
                } else {
                    if (!shareMap.containsKey(hasPakinglot.getParkingId())) {
                        commonMap.put(hasPakinglot.getParkingId(), hasPakinglot);
                    }
                }
            }else {
                commonMap.put(hasPakinglot.getParkingId(), hasPakinglot);
            }
        }
        //map遍历
        Iterator share=shareMap.entrySet().iterator();
        List<ParkingUtil> shareList=new ArrayList<ParkingUtil>();
        while (share.hasNext()){
            Map.Entry entry=(Map.Entry)share.next();
            ParkingUtil p= (ParkingUtil) entry.getValue();
            shareList.add(p);
        }
        Iterator common=commonMap.entrySet().iterator();
        List<ParkingUtil> commonList=new ArrayList<ParkingUtil>();
        while (common.hasNext()){
            Map.Entry entry=(Map.Entry)common.next();
            ParkingUtil p= (ParkingUtil) entry.getValue();
            commonList.add(p);
        }
        return new ParkingList(shareList,commonList);
    }

    public Parking getPakingById(long parkingId) {
        return parkingMapper.selectByPrimaryKey(parkingId);
    }

    public void updateParking(Parking parking) {
        parkingMapper.updateByPrimaryKeySelective(parking);
    }

    public ParkingInfo queryParkingInfo(long parking_id) {
        ParkingInfo parkinggener= parkingMapper.queryParkingInfo(parking_id);
        List<Generate> generateList=parkinggener.getGenerateList();
        List<Generate> res=new ArrayList<Generate>();
        for(int i=0;i<generateList.size();i++){
            Generate g=generateList.get(i);
            long generate_id=g.getGenerateId();
            BigDecimal price=g.getGeneratePrice();
            String begin= DateUtil.longToDate(g.getGenerateShareBegin());
            String end= DateUtil.longToDate(g.getGenerateShareEnd());
            long parId=g.getParkinglotId();
            String status=g.getGenerateStatus();
            Generate s=new Generate(generate_id,parId,price,status,begin,end);
            res.add(s);
        }
        parkinggener.setGenerateList(res);
        return parkinggener;
    }
}