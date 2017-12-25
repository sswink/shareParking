package com.front.util;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Lin on 2017/9/25.
 */
public class encryptingUtil {

    // cookie加密
    public static String encCookie(long cookieId){
        return "A13s" + (cookieId * 313 + 461 - 222) + "S524ddnsub$72-//34)85" + cookieId * 233 + 341 + "f8926812w";
    }

    // cookie解密
//    public static int decCookie(String cookie){
//        return (new Integer(cookie.substring(4, cookie.indexOf("S524ddnsub$72-//34)85"))) + 222 - 461) / 313;
//    }
}
