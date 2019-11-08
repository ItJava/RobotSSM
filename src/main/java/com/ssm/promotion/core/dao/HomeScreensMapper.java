package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.HomeScreens;

public interface HomeScreensMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomeScreens record);

    int  deletHomePicByDeviceId(String deviceId);

    int insertSelective(HomeScreens record);

    HomeScreens selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(HomeScreens record);

    int updateByPrimaryKey(HomeScreens record);
}