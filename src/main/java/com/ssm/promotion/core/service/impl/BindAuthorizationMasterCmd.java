package com.ssm.promotion.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ssm.promotion.core.entity.bean.mqtt.immqttdata.BindMasterMqttData;
import com.ssm.promotion.core.service.*;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class BindAuthorizationMasterCmd implements MqttBaseCmdService {

    public BindMasterMqttData bindMasterMqttData;


    private MqttUserService mqttService;



    private DevicebindService equipmentService;

    private BuserService userService;

    private DeviceService deviceService;


    private BindAuthorizationMasterCmd() {

    }


    public static BindAuthorizationMasterCmd getInstance() {
        return SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static BindAuthorizationMasterCmd instance = new BindAuthorizationMasterCmd();
    }


    public void setServicce(MqttUserService wx2MqttService, DevicebindService cmdEquipmentService, BuserService cmdUserService, DeviceService cmdDeviceService) {
        mqttService = wx2MqttService;
        equipmentService = cmdEquipmentService;
        userService = cmdUserService;
        deviceService = cmdDeviceService;
    }


    public void runMasterCmd(String cmd, String data) {
        bindMasterMqttData = new BindMasterMqttData();
        JSONObject obj = JSONObject.parseObject(data);
        String device_id = obj.getString("device_id");
        String push_time = obj.getString("push_time");
        String push_data = obj.getString("push_data");
        int push_source = obj.getIntValue("push_source");
        String toType = obj.getString("toType");
        String user_name = obj.getString("user_name");
        int parameter1 = obj.getIntValue("parameter1");      //代表当前的type  类型
        String parameter2 = obj.getString("parameter2");
        String parameter3 = obj.getString("parameter3");   //授权结果 0：允许  1：拒绝

        bindMasterMqttData.device_id = device_id;
        bindMasterMqttData.cmd = cmd;
        bindMasterMqttData.fromType = "server";
        bindMasterMqttData.push_time = push_time;
        bindMasterMqttData.pushData = push_data;
        bindMasterMqttData.pushSource = push_source;
        bindMasterMqttData.toType = toType;
        bindMasterMqttData.userName = user_name;
        bindMasterMqttData.parameter1 = parameter1;
        bindMasterMqttData.parameter2 = parameter2;
        bindMasterMqttData.parameter3 = parameter3;


    }

    @Override
    public void exe() {
       /* int authorizationFlag = Integer.parseInt(bindMasterMqttData.parameter3);
        //手机端根据 parameter3 判断是否允许授权
        if (authorizationFlag == 0) {     //允许授权成功
            bindMasterMqttData.cmd = "grant_master_authorization_result";
            bindMasterMqttData.parameter1 = 0;         //允许授权
            equipmentService.save(new UserEquipMent(bindMasterMqttData.device_id, bindMasterMqttData.userName, 1, new Date()));
            User user = userService.getByUserName(bindMasterMqttData.userName);
            Device device = deviceService.getByDeviceIdAndType(bindMasterMqttData.device_id, Integer.parseInt(bindMasterMqttData.parameter2));
            ReturnBindDeviceMsg returnBindDeviceMsg = new ReturnBindDeviceMsg(1, bindMasterMqttData.device_id, bindMasterMqttData.userName,
                    device.getDeviceName(), user.getPhotoUrl(), device.getDevicePic());
            String dataMsg = new Gson().toJson(returnBindDeviceMsg);

            bindMasterMqttData.pushData = dataMsg;
            String bindData = new Gson().toJson(bindMasterMqttData);
//            System.out.println("-----发送给设备端和手机段的推送数据----------->" + bindData);
            mqttService.publish("/device/wanxiang/user/server/device/1.0/cn/" + bindMasterMqttData.device_id, new MqttMessage(bindData.getBytes()));
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(bindData.getBytes()));


        } else {                       //拒绝授权
            bindMasterMqttData.cmd = "grant_master_authorization_result";
            bindMasterMqttData.parameter1 = 1;         //拒绝授权
            bindMasterMqttData.fromType = "server";
            bindMasterMqttData.toType = "user";
            String bindData = new Gson().toJson(bindMasterMqttData);
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(bindData.getBytes()));
        }*/

    }
}
