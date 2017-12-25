package com.front.dao;

import com.front.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminMapper {
    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin getAdminByName(String adminName);

    Admin getAdminByNameAndPassword(Map<String,String> map);

    List<Admin> queryAll();
}