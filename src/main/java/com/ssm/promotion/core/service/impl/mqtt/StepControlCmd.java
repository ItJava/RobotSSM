package com.ssm.promotion.core.service.impl.mqtt;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ssm.promotion.core.entity.bean.mqtt.immqttdata.MusicMqttData;
import com.ssm.promotion.core.service.MqttBaseCmdService;
import com.ssm.promotion.core.service.MqttUserService;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class StepControlCmd implements MqttBaseCmdService {

    public MusicMqttData musicMqttData;

    private MqttUserService mqttService;

    public StepControlCmd(String cmd, String data, MqttUserService wx2MqttService) {
        musicMqttData = new MusicMqttData();
        mqttService = wx2MqttService;

        //json.optString("projectName"))

        JSONObject obj = JSONObject.parseObject(data);
        String device_id = obj.getString("device_id");
        String push_time = obj.getString("push_time");
        String push_data = obj.getString("push_data");
        int push_source = obj.getIntValue("push_source");
        String toType = obj.getString("toType");
        String user_name = obj.getString("user_name");
        int parameter1 = obj.getIntValue("parameter1");

        musicMqttData.device_id = device_id;
        musicMqttData.cmd = cmd;
        musicMqttData.fromType = "server";
        musicMqttData.push_time = push_time;
        musicMqttData.pushData = push_data;
        musicMqttData.pushSource = push_source;
        musicMqttData.toType = toType;
        musicMqttData.userName = user_name;
        musicMqttData.parameter1 = parameter1;
    }


    @Override
    public void exe() {
        String musicData = new Gson().toJson(musicMqttData);
//        System.out.println("发送了消息出去：发布主题：/device/wanxiang/user/server/device/1.0/cn/"+musicMqttData.device_id);
        mqttService.publish("/device/wanxiang/user/server/device/1.0/cn/" + musicMqttData.device_id, new MqttMessage(musicData.getBytes()));  //new Gson().toJson(musicMqttData).getBytes());
    }


}
