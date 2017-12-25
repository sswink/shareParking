package com.front.Service;

import com.front.dto.AdminDetail;
import com.front.dto.Result;
import com.front.entity.Admin;

import java.util.List;

/**
 * Created by yingmanji on 2017/9/27.
 */
public interface AdminService {

    public List<AdminDetail> getAdminDetailList();

    public Admin loginAdmin(String adminName, String adminPassword);

    public Result createAdmin(String adminName, String adminPassword, long adminParkingId) throws Exception;

    public Result alterAdminPassword(String adminName, String adminOldPassword, String adminNewPassword);

    public Result deleteAdmin(String adminName);

    public Admin getAdminByName(String adminName);

}
