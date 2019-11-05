package com.iot.wx.bean;


import com.google.gson.Gson;

public class WxBaseMsg {

    public static final String MSG_TYPE_BIND = "bind";
    public static final String MSG_TYPE_UNBIND = "unbind";

    public static final String MSG_TYPE_GET = "get";
    public static final String MSG_TYPE_SET = "set";
    /**
     * msg_type : get
     */
    @SuppressWarnings("WeakerAccess")
    protected String msg_type;

    public static WxBaseMsg fromJson(String json) {
        //return WxGsonBuilder.create().fromJson(json, WxBaseMsg.class);


        return new Gson().fromJson(json, WxBaseMsg.class);


    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }
}
