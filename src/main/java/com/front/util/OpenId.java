package com.front.util;

import com.front.dto.GetByWx;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class OpenId {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private com.front.util.IdPackage IdPackage;
    public GetByWx getOpenId(String num) {
        GetByWx wxms = new GetByWx();
        String status = "1";
        String msg = "ok";
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        /** 连接池配置信息 **/
        JedisPoolConfig config = new JedisPoolConfig();
        /** 生成连接池 **/
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        /** 从连接池获取连接 **/
        Jedis client = pool.getResource();

        try {
            if (StringUtils.isBlank(num)) {
                status = "0";//失败状态
                msg = "code为空";
            } else {
                String requestUrl = WX_URL.replace("APPID", WeiXinConstants.APPID).
                        replace("SECRET", WeiXinConstants.APP_SECRECT).replace("JSCODE", num).
                        replace("authorization_code", WeiXinConstants.AUTHORIZATION_CODE);
                logger.info(requestUrl);
                // 发起GET请求获取凭证
                JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

                if (jsonObject != null) {
                    try {
                        //wxms.setSession_key(jsonObject.getString("session_key"));
                        String openid = jsonObject.getString("openid");
                        //封装openid
                        String rds = IdPackage.getrds(openid);
                        //判断redis是否存在此键
                        if (!client.exists(rds)) {
                            //没有则存入redis
                            client.set(rds, openid);
                        }
                        wxms.setOpenID(openid);
                        wxms.setRds(rds);
                        System.out.println(rds);
                    } catch (JSONException e) {
                        // 获取token失败
                        status = "0";
                        msg = "code无效";
                    }
                } else {
                    status = "0";
                    msg = "code无效";
                }
            }
            wxms.setStatus(status);
            wxms.setMsg(msg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //return AnalyzeMoblieData.errorResponse();
        } finally {
            /** 业务操作完成，将连接返回给连接池 **/
            if (null != client) {
                pool.close();
            }
        }
        return wxms;

    }

    public static void main(String[] args) {
        String s="    ";
        System.out.println(s.length());
        //System.out.println("s:"+s.trim());
        System.out.println(s.trim().length());
        System.out.println("".length());
    }
}