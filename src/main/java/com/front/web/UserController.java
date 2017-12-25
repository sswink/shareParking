package com.front.web;

import com.front.Service.UsersService;
import com.front.dao.UsersMapper;
import com.front.dto.GetByWx;
import com.front.dto.Login;
import com.front.dto.Result;
import com.front.entity.Users;
import com.front.util.IdPackage;
import com.front.util.OpenId;
import com.front.util.SendSms;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yingmanji on 2017/9/27.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private IdPackage idpackage;
    @Autowired
    private OpenId openId;
    @Autowired
    private SendSms sendsms;
    @Autowired
    private UsersService usersService;

    //todo 获取所有用户信息
    @ApiOperation(value = "获取所有用户信息",notes = "获取所有用户信息",httpMethod = "GET")
    @RequestMapping(method = RequestMethod.GET,value = "/getAllUser")
    @ResponseBody
    public Result getAllUser(){
        return usersService.getAllUser();
    }

    private static Integer phone_codes;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //manager.js(rds用户登录获取信息 rds->redis取openID->取userID)
    @ApiOperation(value = "rds登录获取用户信息", notes = "用户登录获取信息并更新最后登录时间", httpMethod = "POST", response = Login.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{rds}/rdslogin")
    public Result<Login> login(HttpServletRequest request
    ) {
        try {
            String rds=request.getParameter("rds");
            String wxopen_id = idpackage.getOpenId(rds);
            System.out.println(wxopen_id);
            Users user = usersService.login(wxopen_id);
            Login login =new Login(user.getWxName(),user.getUserName(),user.getSex(),user.getPhoneNum(),user.getPlateNum(),rds);
            if(user==null){
                return new Result<Login>(false, "sql没有信息");
            }
            return new Result<Login>(true, login);
        } catch (Exception e) {
            return new Result<Login>(false, e.getMessage());
        }
    }
    //manager.js(code用户登录获取信息 code->服务器获得openID->加密后存入redis)
    @ApiOperation(value = "code登录获取用户信息", notes = "用户登录获取信息并更新最后登录时间", httpMethod = "POST", response = Login.class)
    @RequestMapping(method = RequestMethod.POST, value = "/{code}/codelogin")
    public Result codelogin(HttpServletRequest request
    ) {
        try {
            String code=request.getParameter("code");
            GetByWx getByWx=openId.getOpenId(code);
            String wxopen_id = getByWx.getOpenID();
            String rds=idpackage.getrds(wxopen_id);
            System.out.println(wxopen_id);
            Users user = usersService.login(wxopen_id);
            Login login;
            if(user==null){
                //login=new Login(rds);
                return new Result(false, "sql没有信息");
            }
            login=new Login(user.getWxName(),user.getUserName(),user.getSex(),user.getPhoneNum(),user.getPlateNum(),rds);
            return new Result(true, login);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }
    //register.js(用户的注册)
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST", response = Boolean.class)
    @RequestMapping(method = RequestMethod.POST, value = "/regist")
    public Result<Boolean> newUser(HttpServletRequest request
    ) {
        try {
            String code=request.getParameter("code");
            String wx_name=request.getParameter("nick");
            String user_name=request.getParameter("name");
            String sex=request.getParameter("sex");
            String phone_num=request.getParameter("phone");
            String plate_num=request.getParameter("plate_number");
            String phone_code=request.getParameter("phone_code");
            //String sms = httpSession.getAttribute("smss").toString();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastTime = s.format(new Date().getTime());

            //取得返回信息
            GetByWx getByWx = openId.getOpenId(code);
            //System.out.println(wxmsg.getOpenID());
            //System.out.println(sms);
            long phone_cod=Integer.parseInt(phone_code);
            //验证码匹对
            if (phone_cod == phone_codes) {
                usersMapper.insertUser(getByWx.getOpenID(), wx_name, user_name, sex, phone_num, plate_num, lastTime);
                return new Result<Boolean>(true, true);
            }else {
                return new Result<Boolean>(false, "验证码输入有误");
            }
//            return new Result<Boolean>(false, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result<Boolean>(false, e.getMessage());
        }
    }

    //register.js -> utils-util.js(发送验证码)
    @ApiOperation(value = "发送验证码", notes = "手机号", httpMethod = "POST", response = Boolean.class)
    @RequestMapping(value = "/{phone}/sendCode", method = RequestMethod.POST)
    public Result<Integer> sensms(HttpSession httpSession,
                                  @ApiParam(required = true, name = "phone", value = "手机号") @PathVariable("phone") String phone){
        try{
            //发送验证码并获取
            phone_codes=sendsms.getSms(phone);
            //httpSession.setAttribute("smss",sms);
            System.out.println(phone_codes);
            return new Result<Integer>(true, phone_codes);
        }catch (Exception e){
            return new Result<Integer>(false, "验证码发送错误");
        }
    }
}