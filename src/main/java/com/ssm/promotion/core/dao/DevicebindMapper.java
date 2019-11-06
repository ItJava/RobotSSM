package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Devicebind;

import java.util.List;

public interface DevicebindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicebind record);

    int insertSelective(Devicebind record);

    Devicebind selectByPrimaryKey(Integer id);

    List<Devicebind> selectByPhone(String phone);
    List<Devicebind> selectByDeviceId(String deviceId);

    Devicebind selectByPhoneAndDevice(String phone, String deviceId);

    int updateByPrimaryKeySelective(Devicebind record);

    int updateByPrimaryKey(Devicebind record);

    int deletBindDeviceByDeviceIdAndPhone(String phone, String deviceId);

    int deletBindDeviceAllByDeviceId(String deviceId);

    int deletBindDeviceAllByPhone(String phone);






}