package com.ssm.promotion.core.entity.bean.mqtt;

import java.io.Serializable;

public class AddDeviceBean implements Serializable {
    /**
     * from_type : user
     * to_type : user_server
     * cmd : add_device
     * user_name : 15217764126
     * device_id : robotID
     */
    private String from_type;
    private String to_type;
    private String cmd;
    private String user_name;
    private String device_id;
    private int resultCode;
    private String msg;


    public String getFrom_type() {
        return from_type;
    }

    public void setFrom_type(String from_type) {
        this.from_type = from_type;
    }

    public String getTo_type() {
        return to_type;
    }

    public void setTo_type(String to_type) {
        this.to_type = to_type;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
