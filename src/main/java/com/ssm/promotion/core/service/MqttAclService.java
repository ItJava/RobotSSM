package com.ssm.promotion.core.service;


import com.ssm.promotion.core.entity.MqttAcl;

public interface MqttAclService {


    int deleteByPrimaryKey(Integer id);


    int insert(MqttAcl record);


    int insertSelective(MqttAcl record);


    MqttAcl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MqttAcl record);


    int updateByPrimaryKey(MqttAcl record);

}
