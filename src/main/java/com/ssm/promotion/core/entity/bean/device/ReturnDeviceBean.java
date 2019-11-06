package com.ssm.promotion.core.entity.bean.device;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ReturnDeviceBean implements Serializable {


    private String deviceId;      //产品ID
    private String phoneLic;       //第三方通话lic
    private String deviceName;    //设备名称
    private String devicePic;     //设备名称
    private Date birthDay;        //机器人生日


    public ReturnDeviceBean() {

    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneLic() {
        return phoneLic;
    }

    public void setPhoneLic(String phoneLic) {
        this.phoneLic = phoneLic;
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
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
