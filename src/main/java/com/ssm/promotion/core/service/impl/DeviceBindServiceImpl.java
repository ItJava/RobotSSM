package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.DevicebindMapper;
import com.ssm.promotion.core.entity.Devicebind;
import com.ssm.promotion.core.service.DevicebindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("deviceBind")
public class DeviceBindServiceImpl implements DevicebindService {

    @Resource
    private DevicebindMapper devicebindMapper;


    public int deleteByPrimaryKey(Integer id) {
        return devicebindMapper.deleteByPrimaryKey(id);
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


    public int updateByPrimaryKeySelective(Devicebind record) {
        return devicebindMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Devicebind record) {
        return devicebindMapper.updateByPrimaryKey(record);
    }


}
