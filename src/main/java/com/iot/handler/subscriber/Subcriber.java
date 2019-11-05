package com.iot.handler.subscriber;

public interface Subcriber<MqttMessage> {

    public abstract void handle(String deviceId, MqttMessage msg);


}
