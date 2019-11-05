package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.MqttAclMapper;
import com.ssm.promotion.core.entity.MqttAcl;
import com.ssm.promotion.core.service.MqttAclService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("mqttAclService")
public class MqttAclServiceImpl implements MqttAclService {
    @Resource
    private MqttAclMapper mqttAclMapper;


    public int deleteByPrimaryKey(Integer id) {
        return mqttAclMapper.deleteByPrimaryKey(id);
    }


    public int insert(MqttAcl record) {
        return mqttAclMapper.insert(record);
    }


    public int insertSelective(MqttAcl record) {
        return mqttAclMapper.insertSelective(record);
    }


    public MqttAcl selectByPrimaryKey(Integer id) {
        return mqttAclMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(MqttAcl record) {
        return mqttAclMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(MqttAcl record) {
        return mqttAclMapper.updateByPrimaryKey(record);
    }


}
