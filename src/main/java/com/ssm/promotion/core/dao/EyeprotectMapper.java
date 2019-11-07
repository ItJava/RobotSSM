package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.entity.Eyeprotect;

public interface EyeprotectMapper {

    int insert(Eyeprotect record);

    int insertSelective(Eyeprotect record);

    Eyeprotect selectByPrimaryKey(String deviceId);

     int updateByPrimaryKey(Eyeprotect record);

}