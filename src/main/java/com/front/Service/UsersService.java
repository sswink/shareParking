package com.front.Service;

import com.front.dto.Result;
import com.front.entity.Users;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yingmanji on 2017/9/27.
 */
public interface UsersService {

    Result getAllUser();

    //登录
    Users login(String wxopen_id);
}