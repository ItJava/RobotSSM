package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Chat;

public interface ChatService {


    int deleteByPrimaryKey(Integer id);


    int insert(Chat record);


    int insertSelective(Chat record);


    Chat selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Chat record);


    int updateByPrimaryKey(Chat record);

}
