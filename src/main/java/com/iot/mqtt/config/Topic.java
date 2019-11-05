package com.iot.mqtt.config;


public class Topic {

    //用来分路由处理的：
    /*
    /user/wanxiang/server/1.0/cn/{deviceId}【格式：来源_wanxiang_去处】
    /device/wanxiang/server/1.0/cn/{userId}
     */
    public static final String TOPTIC_SERVER_USER = "/user/wanxiang/server/1.0/cn/#";        //user发给server端
    public static final String TOPTIC_SERVER_DEVICE = "/device/wanxiang/server/1.0/cn/#";    //设备发给server端

    /*
    发布主题
    /device/wanxiang/user/server/device/1.0/cn/{deviceId}  格式：客户端类型—_wanxiang_来源-server-目的地
    /user/wanxiang/device/server/user/1.0/cn/{deviceId}
     */
    public static final int[] QOS = {2, 2};
    public static final String[] TOPICS = {
            TOPTIC_SERVER_USER,
            TOPTIC_SERVER_DEVICE,
     };
    //用来publish出的
//    public static final String USER_BIND= "user/{username}/bind/{device_id}";
    public static final String SERVER_BIND_PREFIX = "server/bind/";
    public static final String SERVER_UNBIND_PREFIX = "server/unbind/";
    public static final String SERVER_SET_PREFIX = "server/set/";
    public static final String SERVER_GET_PREFIX = "server/get/";

}
