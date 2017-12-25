package com.front.dao;

import com.front.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<Users> getAllUser();

    //通过wxopenID查询user
    Users queryBywxOpenId(@Param("wxopen_id") String  wxopen_id);
    //通过wxopen_id更新最后一次登录时间
    int update(@Param("lastlogin_time") String lastlogin_time,@Param("wxopen_id") String  wxopen_id);
    //新增用户
    int insertUser(@Param("wxopen_id") String wxopen_id,
                   @Param("wx_name") String wx_name,
                   @Param("user_name") String user_name,
                   @Param("sex") String sex,
                   @Param("phone_num") String phone_num,
                   @Param("plate_num") String plate_num,
                   @Param("lastlogin_time") String lastlogin_time);

}