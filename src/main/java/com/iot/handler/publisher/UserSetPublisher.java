package com.iot.handler.publisher;

import com.iot.mqtt.config.Topic;
import com.iot.wx.bean.WxIotMsg;
import com.ssm.promotion.core.service.impl.MqttUserServiceImpl;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class UserSetPublisher extends Publisher<WxIotMsg> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserSetPublisher(MqttUserServiceImpl wx2MqttService) {
        super(wx2MqttService);
    }

    @Override
    public boolean handle(String json, WxIotMsg wxIotMsg) {
        if (wxIotMsg == null || wxIotMsg.getDevice_id() == null) return false;
        String topic = Topic.SERVER_SET_PREFIX;
        topic += wxIotMsg.getDevice_id();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(json.getBytes());
        mqttMessage.setQos(2);
        mqttMessage.setRetained(false);
        if (wx2MqttService == null) logger.error("(wx2MqttService==null)");
        //  wx2MqttService.publish(topic, mqttMessage);
        return true;
    }
}
