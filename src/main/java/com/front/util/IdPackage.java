package com.front.util;

import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class IdPackage {
    /** 连接池配置信息 **/
    JedisPoolConfig config = new JedisPoolConfig();
    /** 生成连接池 **/
    JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
    /** 从连接池获取连接 **/
    Jedis client = pool.getResource();
    private static String slat = "[]qweqwe23[12]312";
    public static String packageNum(long num){
        String pack="16304"+num+"15304";
        return pack;
    }
    public static String rePackage(String num){
        int beginIndex=num.indexOf("4");
        int endIndex=num.lastIndexOf("1");
        String renum=num.substring(beginIndex+1,endIndex);
        return renum;
    }
    public  String getrds(String OpenId){
        String base=OpenId+"/"+slat;
        String rds = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println(rds);
        return rds;
    }
    public String getOpenId(String rds){
        try{
            String OpenId=client.get(rds);
            return OpenId;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            /** 业务操作完成，将连接返回给连接池 **/
            if (null != client) {
                pool.close();
            }
        }
        /** 应用关闭时，释放连接池资源 **/
        pool.destroy();
        return "0";
    }

    public static void main(String[] args) {
        /** 连接池配置信息 **/
        JedisPoolConfig config = new JedisPoolConfig();
        /** 生成连接池 **/
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        /** 从连接池获取连接 **/
        Jedis client = pool.getResource();
        String OpenId=client.get("9382fafc405d2510147792050a84b857");
        System.out.println(OpenId);
    }
}