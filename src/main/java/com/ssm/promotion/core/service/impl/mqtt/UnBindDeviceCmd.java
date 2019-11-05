package com.ssm.promotion.core.service.impl.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.ssm.promotion.core.entity.bean.mqtt.immqttdata.BindMasterMqttData;
import com.ssm.promotion.core.service.*;

public class UnBindDeviceCmd implements MqttBaseCmdService {

    public BindMasterMqttData bindMasterMqttData;

    private MqttUserService mqttService;

    private DevicebindService equipmentService;
    private BuserService userService;
    private DeviceService deviceService;

    private UnBindDeviceCmd() {
    }

    public static UnBindDeviceCmd getInstance() {
        return SingletonHolder.instance;
    }


    private static class SingletonHolder {
        private static UnBindDeviceCmd instance = new UnBindDeviceCmd();
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
            parameter2 = obj.getIntValue("parameter2");   //代表当前是否是管理员 【master|normal_master】
            bindMasterMqttData.parameter2 = String.valueOf(parameter2);
        } else {
//            System.out.println("当前没有类型，必须传递当前设备端类型：");

            return;
        }
        if (obj.containsKey("parameter3")) {
            parameter3 = obj.getString("parameter3");
            bindMasterMqttData.parameter3 = parameter3;
        } else {
//            System.out.println("当前没有类型，必须传递当前设备端类型：");

            return;
        }


    }


    @Override
    public void exe() {

       /* //判断当前,设备是否已经绑定
        UserEquipMent userEquipMent = null;
        try {
            userEquipMent = equipmentService.getEquitmentMasterByDeviceId(bindMasterMqttData.device_id);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (bindMasterMqttData.parameter2.equals("0")) {     //管理员
            ReturnUnBindDevice returnUnBindDevice = new ReturnUnBindDevice(bindMasterMqttData.device_id, bindMasterMqttData.userName);
            if (returnUnBindDevice == null) {
                return;
            }
            String jsonData = new Gson().toJson(returnUnBindDevice);
            bindMasterMqttData.pushData = jsonData;
            String returnData = new Gson().toJson(bindMasterMqttData);
 //           System.out.println("----准备发送个 设备端、管理员、多个已经绑定的普通管理员解绑信息---------->" + returnData);
            List<UserEquipMent> userEquipMents = equipmentService.getEquitmentsByDeviceId(bindMasterMqttData.device_id);

            for (UserEquipMent equipMent : userEquipMents) {
                mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + equipMent.getClientId(), new MqttMessage(returnData.getBytes()));   //普通管理员
                equipmentService.delete(equipMent);

            }
            mqttService.publish("/device/wanxiang/user/server/device/1.0/cn/" + bindMasterMqttData.device_id, new MqttMessage(returnData.getBytes()));   //设备端
            //mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + userEquipMent.getClientId(), new MqttMessage(returnData.getBytes()));   //管理员
        } else {
            //删掉指定的客户端,并推送给管理员和它自己对应的设备端
            UserEquipMent userEquipMent1 = equipmentService.getByDeviceIdAndClientId(bindMasterMqttData.device_id, bindMasterMqttData.userName);

            if (userEquipMent1 == null) {
                return;
            }

            ReturnUnBindDevice returnUnBindDevice = new ReturnUnBindDevice(bindMasterMqttData.device_id, bindMasterMqttData.userName);
            String jsonData = new Gson().toJson(returnUnBindDevice);
            bindMasterMqttData.pushData = jsonData;
            bindMasterMqttData.toType = "device|user";
            String returnData = new Gson().toJson(bindMasterMqttData);
 //           System.out.println("----准备发送个 设备端、管理员、普通管理员解绑信息---------->" + returnData);
            equipmentService.delete(userEquipMent1);

            mqttService.publish("/device/wanxiang/user/server/device/1.0/cn/" + bindMasterMqttData.device_id, new MqttMessage(returnData.getBytes()));   //设备端
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + bindMasterMqttData.userName, new MqttMessage(returnData.getBytes()));   //普通管理员
            mqttService.publish("/user/wanxiang/device/server/user/1.0/cn/" + userEquipMent.getClientId(), new MqttMessage(returnData.getBytes()));   //管理员
*/


    }

}

