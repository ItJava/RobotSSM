package com.iot.wx.bean;


import com.iot.wx.bean.BothSyncResponse;

public class WxSyncResponseForNotifyMsg extends BothSyncResponse {
    private long msg_id;

    public static WxSyncResponseForNotifyMsg fromJson(String json) {
        //  return WxGsonBuilder.create().fromJson(json, WxSyncResponseForNotifyMsg.class);
        return null;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(long msg_id) {
        this.msg_id = msg_id;
    }

}
