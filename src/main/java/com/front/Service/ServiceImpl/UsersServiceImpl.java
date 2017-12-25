package com.front.Service.ServiceImpl;

import com.front.Service.UsersService;
import com.front.dao.UsersMapper;
import com.front.dto.Result;
import com.front.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yingmanji on 2017/9/27.
 */
@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersMapper usersMapper;

    public Result getAllUser() {
        return new Result(true,usersMapper.getAllUser());
    }

    public Users login(String wxopen_id) {
        Users user=usersMapper.queryBywxOpenId(wxopen_id);
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lastLoginTime=s.format(new Date().getTime());
        usersMapper.update(lastLoginTime,wxopen_id);
        return user;
    }
}