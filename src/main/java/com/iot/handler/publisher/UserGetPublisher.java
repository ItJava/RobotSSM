package com.iot.handler.publisher;


import com.iot.mqtt.config.Topic;
import com.iot.wx.bean.WxIotMsg;
import com.ssm.promotion.core.service.impl.MqttUserServiceImpl;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;


@Component
public class UserGetPublisher extends Publisher<WxIotMsg> {

    public UserGetPublisher(MqttUserServiceImpl wx2MqttService) {
        super(wx2MqttService);
    }

    @Override
    public boolean handle(String json, WxIotMsg wxIotMsg) {
        if (wxIotMsg == null || wxIotMsg.getDevice_id() == null) return false;
        String topic = Topic.SERVER_GET_PREFIX;
        topic += wxIotMsg.getDevice_id();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(json.getBytes());
        mqttMessage.setQos(1);
        mqttMessage.setRetained(false);
        // wx2MqttService.publish(topic, mqttMessage);
        return true;
    }
}
