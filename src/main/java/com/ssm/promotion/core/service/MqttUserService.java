package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.MqttUser;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttUserService {


    int deleteByPrimaryKey(Integer id);


    int insert(MqttUser record);


    int insertSelective(MqttUser record);


    MqttUser selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(MqttUser record);


    int updateByPrimaryKey(MqttUser record);

    boolean publish(String topicStr, MqttMessage mqttMessage);


}
