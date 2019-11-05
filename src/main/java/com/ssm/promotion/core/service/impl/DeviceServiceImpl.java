package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.DeviceMapper;
import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {


    @Resource
    private DeviceMapper deviceMapper;





    public int deleteByPrimaryKey(String deviceId) {
        return deviceMapper.deleteByPrimaryKey(deviceId);
    }


    public int insert(Device record) {
        return deviceMapper.insert(record);
    }


    public int insertSelective(Device record) {
        return deviceMapper.insertSelective(record);
    }


    public Device selectByPrimaryKey(String deviceId) {
        return deviceMapper.selectByPrimaryKey(deviceId);
    }


    public int updateByPrimaryKeySelective(Device record) {
        return deviceMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Device record) {
        return deviceMapper.updateByPrimaryKey(record);
    }
}
