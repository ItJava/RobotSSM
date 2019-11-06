package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Phonetoken;


public interface PhonetokenService {


    int deleteByPrimaryKey(Integer id);


    int insert(Phonetoken record);


    int insertSelective(Phonetoken record);


    Phonetoken selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Phonetoken record);


    int updateByPrimaryKey(Phonetoken record);

}
