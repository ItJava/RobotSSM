package com.ssm.promotion.core.service;
import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.entity.Eyeprotect;

public interface EyeprotectService {
    int insert(Eyeprotect record);

    int insertSelective(Eyeprotect record);

    Eyeprotect selectByPrimaryKey(String deviceId);

    int upDateEyeprotectAlarm(Eyeprotect record);


}

