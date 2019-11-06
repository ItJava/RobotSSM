package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Phonetoken;

public interface PhonetokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Phonetoken record);

    int insertSelective(Phonetoken record);

    Phonetoken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Phonetoken record);

    int updateByPrimaryKey(Phonetoken record);
}