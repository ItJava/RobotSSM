package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.EyeprotectMapper;
import com.ssm.promotion.core.entity.Device;
import com.ssm.promotion.core.entity.Eyeprotect;
import com.ssm.promotion.core.service.EyeprotectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("eyeprotect")
 public class EyeprotectServiceImpl implements EyeprotectService {

    @Resource
    private EyeprotectMapper eyeprotectMapper;


    public int insert(Eyeprotect record) {
        return eyeprotectMapper.insert(record);
    }


    public int insertSelective(Eyeprotect record) {
        return eyeprotectMapper.insertSelective(record);
    }

    @Override
    public Eyeprotect selectByPrimaryKey(String deviceId) {
        return eyeprotectMapper.selectByPrimaryKey(deviceId);
    }

    @Override
    public int updateByPrimaryKey(Eyeprotect record) {
        return eyeprotectMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKey(String deviceId) {
        return eyeprotectMapper.deleteByPrimaryKey(deviceId);
    }

}

