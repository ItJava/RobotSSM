package com.ssm.promotion.core.entity.bean.mqtt;

import com.google.gson.annotations.SerializedName;

public class MqttBaseData {

    @SerializedName("from_type")
    public String fromType;
    @SerializedName("toType")
    public String toType;
    @SerializedName("cmd")
    public String cmd;
    @SerializedName("user_name")
    public String userName;
    @SerializedName("device_id")
    public String device_id;
    @SerializedName("push_time")
    public String push_time;
    @SerializedName("push_data")
    public String pushData;
    @SerializedName("push_source")
    public int pushSource;
    @SerializedName("parameter1")
    public int parameter1;
    @SerializedName("parameter2")
    public String parameter2;
    @SerializedName("parameter3")
    public String parameter3;

}
