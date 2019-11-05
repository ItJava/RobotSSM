package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.MqttUser;

public interface MqttUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MqttUser record);

    int insertSelective(MqttUser record);

    MqttUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MqttUser record);

    int updateByPrimaryKey(MqttUser record);
}