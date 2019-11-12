package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.QiniuyunAc;

public interface QiniuyunAcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QiniuyunAc record);

    int insertSelective(QiniuyunAc record);

    QiniuyunAc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QiniuyunAc record);

    int updateByPrimaryKey(QiniuyunAc record);
}