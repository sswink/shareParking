package com.front.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Lin on 2017/9/17.
 */
@Controller
public class PageController {

    private final String root = "/WEB-INF/views/";

    @RequestMapping(value = {"/toIndex","/",""},method = RequestMethod.GET)
    public String index(){
        return root+"index.html";
    }

    // 访问url 和 访问的文件名（不包括后缀） 不能一致
//    @RequestMapping(value = "/l",method = RequestMethod.GET)
//    public String l(){
//        return "/l.html";
//    }

    @RequestMapping(value = "/toUser",method = RequestMethod.GET)
    public String user(){
        return root+"user.html";
    }

    @RequestMapping(value = "/toParkmanager",method = RequestMethod.GET)
    public String parkmanager(){
        return root+"parkmanager.html";
    }

    @RequestMapping(value = "/toOrders",method = RequestMethod.GET)
    public String orders(){
        return root+"orders.html";
    }

    @RequestMapping(value = "/toGenerate",method = RequestMethod.GET)
    public String generate(){
        return root+"generate.html";
    }

    @RequestMapping(value = "/toNewParking",method = RequestMethod.GET)
    public String newParking(){
        return root+"newParking.html";
    }

    @RequestMapping(value = "/toParkinglot",method = RequestMethod.GET)
    public String parkinglot(){
        return root+"parkinglot.html";
    }

    @RequestMapping(value = "/toLicense",method = RequestMethod.GET)
    public String license(){
        return root+"license.html";
    }

    @RequestMapping(value = "/toAdminParking",method = RequestMethod.GET)
    public String adminParking(){
        return root+"adminParking.html";
    }

    @RequestMapping(value = "/toAdminParkingOrders",method = RequestMethod.GET)
    public String adminParkingOrders(){
        return root+"adminParkingOrders.html";
    }

    @RequestMapping(value = "/toAdminParkingGenerates",method = RequestMethod.GET)
    public String toAdminParkingGenerates(){
        return root+"adminParkingGenerates.html";
    }

    @RequestMapping(value = "/toAdminUpdateMessage",method = RequestMethod.GET)
    public String toAdminUpdateMessage(){
        return root+"adminUpdateMessage.html";
    }
}
