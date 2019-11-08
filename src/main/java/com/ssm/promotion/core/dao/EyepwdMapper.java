package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Eyepwd;

public interface EyepwdMapper {
    int deleteByPrimaryKey(String deviceId);

    int insert(Eyepwd record);

    int insertSelective(Eyepwd record);

    Eyepwd selectByPrimaryKey(String deviceId);

    int updateByPrimaryKeySelective(Eyepwd record);

    int updateByPrimaryKey(Eyepwd record);
}