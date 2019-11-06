package com.ssm.promotion.core.entity.bean.device;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class ReturnBindDeviceMsg implements Serializable {

    private String deviceId;      //设备端deviceid
    private String clientId;      //手机段clientId
    private String deviceName;    //设备的名称
    private String devicePic;     //设备端图像
    private Date  deviceBirthDate; //色板生日


    public ReturnBindDeviceMsg() {
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevicePic() {
        return devicePic;
    }

    public void setDevicePic(String devicePic) {
        this.devicePic = devicePic;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDeviceBirthDate() {
        return deviceBirthDate;
    }

    public void setDeviceBirthDate(Date deviceBirthDate) {
        this.deviceBirthDate = deviceBirthDate;
    }
}
