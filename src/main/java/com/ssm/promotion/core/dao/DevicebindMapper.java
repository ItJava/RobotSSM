package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Devicebind;

public interface DevicebindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Devicebind record);

    int insertSelective(Devicebind record);

    Devicebind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicebind record);

    int updateByPrimaryKey(Devicebind record);
}