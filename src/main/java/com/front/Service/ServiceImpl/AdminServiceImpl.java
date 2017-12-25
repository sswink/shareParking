package com.front.Service.ServiceImpl;

import com.front.Service.AdminService;
import com.front.dao.AdminMapper;
import com.front.dao.ParkingMapper;
import com.front.dto.AdminDetail;
import com.front.dto.Result;
import com.front.entity.Admin;
import com.front.entity.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yingmanji on 2017/9/27.
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ParkingMapper parkingMapper;

    public List<AdminDetail> getAdminDetailList() {
        List<Admin> adminList = adminMapper.queryAll();
        List<AdminDetail> adminDetailList = new ArrayList<AdminDetail>();
        for (Admin admin:adminList) {
            AdminDetail adminDetail = new AdminDetail();
            adminDetail.setId(admin.getAdminId());
            adminDetail.setAdminName(admin.getAdminName());
            adminDetail.setAdminPassword(admin.getAdminPassword());
            long parkingId = admin.getParkingId();
            if(parkingId != 0){
                Parking parking = parkingMapper.selectByPrimaryKey(parkingId);
                adminDetail.setParkingName(parking.getParkingName());
                adminDetail.setParkingLocation(parking.getParkingLocation());
            }
            adminDetailList.add(adminDetail);
        }
        return adminDetailList;
    }

    public Admin loginAdmin(String adminName, String adminPassword) {

        Map<String,String> map=new HashMap<String, String>();
        map.put("adminName",adminName);
        map.put("adminPassword",adminPassword);

        Admin admin=adminMapper.getAdminByNameAndPassword(map);

        return admin;
    }

    public Result createAdmin(String adminName, String adminPassword, long adminParkingId) throws Exception{

        // 判断账号名是否存在
        Admin existAdmin=adminMapper.getAdminByName(adminName);
        // 是否查询到数据
        if(existAdmin == null) {
            // 不存在管理员名
            Admin admin = new Admin();
            admin.setAdminName(adminName);
            admin.setAdminPassword(adminPassword);
            admin.setParkingId(adminParkingId);
            // 选择性的插入       保证前面的传入的值不为空
            int count = adminMapper.insertSelective(admin);
            if (count == 1) {
                //插入成功
                return new Result(true,"添加管理员成功");
            }
            return new Result(false, "添加管理员失败");
        }
        return new Result(false, "账号名已存在");

    }

    public Result alterAdminPassword(String adminName, String adminOldPassword, String adminNewPassword) {

        // 通过账号名获取账号信息
        Admin admin = adminMapper.getAdminByName(adminName);
        // 记录原密码
        String sqlAdminOldPassword = admin.getAdminPassword();
        // 判断原密码是否对应
        if (sqlAdminOldPassword.equals(adminOldPassword)) {
            // 如果密码一致，修改密码
            admin.setAdminPassword(adminNewPassword);
            adminMapper.updateByPrimaryKeySelective(admin);
            return new Result(true, "修改密码成功");
        }
        return new Result(true, "旧密码错误");
        /*Admin admin=adminMapper.getAdminByName(adminName);*/

        /*admin.setAdminPassword(adminNewPassword);
        adminMapper.updateByPrimaryKeySelective(admin);

        return new Result(true,"修改密码成功");*/
    }

    public Result deleteAdmin(String adminName) {
        // 通过账号名获取账号信息
        Admin admin = adminMapper.getAdminByName(adminName);
        long adminId = admin.getAdminId();
        int result =  adminMapper.deleteByPrimaryKey(adminId);     //// TODO:是否有返回值
        if(result > 0)
            return new Result(true, "删除管理员成功");
        return new Result(true, "删除管理员失败");
    }

    public Admin getAdminByName(String adminName) {
        // 通过账号名获取账号信息
        return adminMapper.getAdminByName(adminName);
    }
}
