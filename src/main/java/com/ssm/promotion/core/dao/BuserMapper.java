package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Buser;

public interface BuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Buser record);

    int insertSelective(Buser record);

    Buser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Buser record);

    int updateByPrimaryKey(Buser record);

    Buser selectBuserByPhone(String userPhone);

}