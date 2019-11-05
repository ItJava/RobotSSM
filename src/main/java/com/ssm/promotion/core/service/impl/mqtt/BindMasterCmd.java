package com.ssm.promotion.core.service.impl.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ssm.promotion.core.entity.bean.mqtt.immqttdata.BindMasterMqttData;
import com.ssm.promotion.core.service.*;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class BindMasterCmd implements MqttBaseCmdService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BindMasterMqttData bindMasterMqttData;

    private MqttUserService mqttService;


    private DevicebindService equipmentService;

    private BuserService userService;

    private DeviceService deviceService;

    private BindMasterCmd() {
    }

    public static BindMasterCmd getInstance() {
        return SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static BindMasterCmd instance = new BindMasterCmd();
    }


    public void setServicce(MqttUserService wx2MqttService, DevicebindService cmdEquipmentService, BuserService cmdUserService, DeviceService cmdDeviceService) {
        mqttService = wx2MqttService;
        equipmentService = cmdEquipmentService;
        userService = cmdUserService;
        deviceService = cmdDeviceService;
    }

    public void runMasterCmd(String cmd, String data) {
        bindMasterMqttData = new BindMasterMqttData();
        int parameter1 = 0;
        int parameter2 = 0;
        String parameter3 = "";
        JSONObject obj = JSONObject.parseObject(data);
        String device_id = obj.getString("device_id");
        String push_time = obj.getString("push_time");
        String push_data = obj.getString("push_data");
        int push_source = obj.getIntValue("push_source");
        String toType = obj.getString("toType");
        String user_name = obj.getString("user_name");


        bindMasterMqttData.device_id = device_id;
        bindMasterMqttData.cmd = cmd;
        bindMasterMqttData.fromType = "server";
        bindMasterMqttData.push_time = push_time;
        bindMasterMqttData.pushData = push_data;
        bindMasterMqttData.pushSource = push_source;
        bindMasterMqttData.toType = toType;
        bindMasterMqttData.userName = user_name;


        if (obj.containsKey("parameter1")) {
            parameter1 = obj.getIntValue("parameter1");
            bindMasterMqttData.parameter1 = parameter1;
        } else {

        }

        if (obj.containsKey("parameter2")) {
            parameter2 = obj.getIntValue("parameter2");   //代表当前的type  类型
            bindMasterMqttData.parameter2 = String.valueOf(parameter2);
        } else {
            System.out.println("当前没有类型，必须传递当前设备端类型：");
            logger.info("当前没有类型，必须传递当前设备端类型");

            return;
        }
        if (obj.containsKey("parameter3")) {
            parameter3 = obj.getString("parameter3");   //代表当前的type  类型
            bindMasterMqttData.parameter3 = parameter3;
        } else {
            return;
        }
    }


    @Override
    public void exe() {
     /*   //判断:1）device_id 是否有人绑定,若无人绑定,直接绑定,用户是管理员。----》并推送数据给user,告知已经绑定成功|设备端也进行推送告知已经有人绑定了。
        //    2)device_id已经有人绑定了,那么需要申请绑定。推送消息给管理员,有人来绑定某一台机器-》1）管理员确定，Server端绑定逻辑推送给申请绑定的人【并分别推送给客户端当前绑定信息】   2）管理员拒绝，Server端转发消息，拒绝绑定申请
        //判断当前,设备是否已经绑定
        UserEquipMent userEquipMent = null;
        UserEquipMent userNormalEquipMent = null;
        try {
            userEquipMent = equipmentService.getEquitmentMasterByDeviceId(bindMasterMqttData.device_id);      //这只是查找超级管理员是否存在
            userNormalEquipMent = equipmentService.getEquitmentNormalMasterByDeviceId(bindMasterMqttData.userName);      //这只是查找超级管理员是否存在
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userEquipMent != null) {
            //设备已经被绑定,1）请求管理员授权给授权:此时后 又需要分两种情况--》1、请求绑定的是超级管理员自己  2、请求绑定的是管理员自己
            if (userNormalEquipMent != null) {
                bindMasterMqttData.pushData = "您之前已绑定,已经是管理员了！";
                String bindData = new Gson().toJson(bindMasterMqttData);
                bindMasterMqttData.cmd = "grant_has_bind";
                mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(bindData.getBytes()));
                System.out.println("您之前已绑定,您已是管理员！  推送消息回去！");
                logger.info("您之前已绑定,您已是管理员！  推送消息回去！");

                return;
            }


            if (userEquipMent.getClientId().equals(bindMasterMqttData.userName)) {          //请求绑定的是超级管理员它自己
//                Device device = deviceService.getByDeviceIdAndType(bindMasterMqttData.device_id, productType);
//                ReturnBindDeviceMsg returnBindDeviceMsg = new ReturnBindDeviceMsg(0, bindMasterMqttData.device_id,
//                        bindMasterMqttData.userName, device.getDeviceName(),
//                        user.getPhotoUrl(), device.getDevicePic());
//                String dataMsg = new Gson().toJson(returnBindDeviceMsg);
                bindMasterMqttData.pushData = "您之前已绑定,您已是超级管理员！";
                bindMasterMqttData.cmd = "grant_has_bind";
                String bindData = new Gson().toJson(bindMasterMqttData);
                mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(bindData.getBytes()));
                System.out.println("您之前已绑定,您已是超级管理员！  推送消息回去！");
                logger.error("您之前已绑定,您已是超级管理员！  推送消息回去！");
                return;
            }

            //grant_master_authorization
            bindMasterMqttData.cmd = "grant_master_authorization";    //更新cmd 推送给管理员,要求授权
            bindMasterMqttData.toType = "user";    //更新cmd 推送给管理员,要求授权
            String bindData = new Gson().toJson(bindMasterMqttData);
            System.out.println("当前需要转发给管理员授权的json 数据：" + bindData);
            //发送给管理员
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + userEquipMent.getClientId(), new MqttMessage(bindData.getBytes()));
            //同时也要发送给刚才请求绑定的客户端，让其等待管理员同意
            bindMasterMqttData.cmd = "wait_master_authorization";
            String mBindData = new Gson().toJson(bindMasterMqttData);
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(mBindData.getBytes()));


        } else {
            //设备还没有绑定：
            // 1）直接绑定：入库
            // 2）推送信息到device  和 client端
            equipmentService.save(new UserEquipMent(bindMasterMqttData.device_id, bindMasterMqttData.userName, 0, new Date()));
            //User user = userService.getByUserName(bindMasterMqttData.userName);
            User user = userService.getByUserPhone(bindMasterMqttData.userName);
            int productType = Integer.parseInt(bindMasterMqttData.parameter2);
            Device device = deviceService.getByDeviceIdAndType(bindMasterMqttData.device_id, productType);
            ReturnBindDeviceMsg returnBindDeviceMsg = new ReturnBindDeviceMsg(0, bindMasterMqttData.device_id,
                    bindMasterMqttData.userName, device.getDeviceName(),
                    user.getPhotoUrl(), device.getDevicePic());
            String dataMsg = new Gson().toJson(returnBindDeviceMsg);
            bindMasterMqttData.pushData = dataMsg;
            String bindData = new Gson().toJson(bindMasterMqttData);
            System.out.println("-----发送给设备端和手机段的推送数据----------->" + bindData);

            logger.error("-----发送给设备端和手机段的推送数据----------->");

            mqttService.publish("/device/wanxiang/user/server/device/1.0/cn/" + bindMasterMqttData.device_id, new MqttMessage(bindData.getBytes()));
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(bindData.getBytes()));


        }*/
    }
}
