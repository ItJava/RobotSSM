package com.iot.handler.publisher;


import com.iot.handler.subscriber.Subcriber;
import com.ssm.promotion.core.service.impl.MqttUserServiceImpl;
import org.springframework.core.ResolvableType;

import java.lang.reflect.ParameterizedType;


public abstract class Publisher<Msg> {
    @SuppressWarnings("WeakerAccess")
    protected MqttUserServiceImpl wx2MqttService;

    public Publisher(MqttUserServiceImpl wx2MqttService) {
        this.wx2MqttService = wx2MqttService;
    }

    public abstract boolean handle(String json, Msg msg);

    @SuppressWarnings("unchecked")
    private Class<Msg> getMsgClass() {
        return (Class<Msg>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public final boolean handle(String json) {
        ResolvableType resolvableType = ResolvableType.forClass(Subcriber.class);
//        Msg msg= WxGsonBuilder.create().fromJson(json, getMsgClass());
//        return handle(json,msg);


        return true;
    }
}
