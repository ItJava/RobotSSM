package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.EyepwdMapper;
import com.ssm.promotion.core.entity.Eyepwd;
import com.ssm.promotion.core.service.EyepwdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("eyepwd")
public class EyepwdServiceImpl implements EyepwdService {

    @Resource
    private EyepwdMapper eyepwdMapper;

    
    public int deleteByPrimaryKey(String deviceId) {
        return eyepwdMapper.deleteByPrimaryKey(deviceId);
    }

    
    public int insert(Eyepwd record) {
        return eyepwdMapper.insert(record);
    }

    
    public int insertSelective(Eyepwd record) {
        return eyepwdMapper.insertSelective(record);
    }

    
    public Eyepwd selectByPrimaryKey(String deviceId) {
        return eyepwdMapper.selectByPrimaryKey(deviceId);
    }

    
    public int updateByPrimaryKeySelective(Eyepwd record) {
        return eyepwdMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Eyepwd record) {
        return eyepwdMapper.updateByPrimaryKey(record);
    }

}
