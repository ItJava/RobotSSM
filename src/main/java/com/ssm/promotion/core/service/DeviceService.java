package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Device;

public interface DeviceService {

    int deleteByPrimaryKey(String deviceId);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}
