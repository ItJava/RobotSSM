package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.DevicebindMapper;
import com.ssm.promotion.core.entity.Devicebind;
import com.ssm.promotion.core.service.DevicebindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("deviceBind")
public class DeviceBindServiceImpl implements DevicebindService {

    @Resource
    private DevicebindMapper devicebindMapper;


    public int deleteByPrimaryKey(Integer id) {
        return devicebindMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deletBindDeviceByDeviceIdAndPhone(String phone, String deviceId) {
        return devicebindMapper.deletBindDeviceByDeviceIdAndPhone(phone, deviceId);
    }

    @Override
    public int deletBindDeviceAllByDeviceId(String deviceId) {
        return devicebindMapper.deletBindDeviceAllByDeviceId(deviceId);
    }

    @Override
    public int deletBindDeviceAllByPhone(String phone) {
        return devicebindMapper.deletBindDeviceAllByPhone(phone);
    }


    public int insert(Devicebind record) {
        return devicebindMapper.insert(record);
    }


    public int insertSelective(Devicebind record) {
        return devicebindMapper.insertSelective(record);
    }


    public Devicebind selectByPrimaryKey(Integer id) {
        return devicebindMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Devicebind> selectByPhone(String phone) {
        return devicebindMapper.selectByPhone(phone);
    }


    @Override
    public List<Devicebind> selectByDeviceId(String deviceId) {
        return devicebindMapper.selectByDeviceId(deviceId);
    }

    @Override
    public Devicebind selectByPhoneAndDevice(String phone, String deviceId) {
        return devicebindMapper.selectByPhoneAndDevice(phone, deviceId);
    }


    public int updateByPrimaryKeySelective(Devicebind record) {
        return devicebindMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Devicebind record) {
        return devicebindMapper.updateByPrimaryKey(record);
    }


}
