package com.front.web;

import com.front.Service.AdminService;
import com.front.dto.AdminDetail;
import com.front.dto.Result;
import com.front.entity.Admin;
import com.front.util.encryptingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by yingmanji on 2017/9/27.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @RequestMapping(value = "/loginAdmin",method = RequestMethod.POST)
    public Result loginAdmin(String adminName,String adminPassword){

        Admin admin=adminService.loginAdmin(adminName,adminPassword);


        if(admin!=null){
            Long parkingId= admin.getParkingId();
            System.out.println("登录成功");
            return new Result(true, encryptingUtil.encCookie(parkingId));
        }
        else
        {
            System.out.println("登录失败");
            return new Result(true,"error");
        }
    }

    // 注册停车场管理员
    @RequestMapping(value = "{adminName}/{adminPassword}/{adminParkingId}/createAdmin",method = RequestMethod.GET)
    @ResponseBody
    public Result createAdmin(HttpSession session, @PathVariable("adminName") String adminName,
                              @PathVariable("adminPassword") String adminPassword,
                              @PathVariable("adminParkingId") int adminParkingId) throws Exception{
        Result result = adminService.createAdmin(adminName,adminPassword,adminParkingId);

        session.setAttribute("adminName",adminName);
        session.setAttribute("adminPassword",adminPassword);

        return result;
    }

    // 修改停车场管理员密码
    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result alterAdminPassword(String adminName,
                                     String adminOldPassword,
                                     String adminNewPassword) throws Exception{
        return adminService.alterAdminPassword(adminName,adminOldPassword,adminNewPassword);
    }

    // 删除停车场管理员
    @RequestMapping(value = "/deleteAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAdmin(String adminName) throws Exception{
        return adminService.deleteAdmin(adminName);
    }

    // 查询管理员信息
    @RequestMapping(value = "/getAdminDetailList",method = RequestMethod.GET)
    public Result getAdminDetailList() throws Exception {
        // 通过账号名获取账号信息
        return new Result(true,adminService.getAdminDetailList());
    }

    // 查询管理员信息
    @RequestMapping(value = "/getAdmin/{adminName}",method = RequestMethod.GET)
    @ResponseBody
    public Admin getAdmin(@PathVariable("adminName") String adminName) throws Exception {
        // 通过账号名获取账号信息
        return adminService.getAdminByName(adminName);
    }
}
