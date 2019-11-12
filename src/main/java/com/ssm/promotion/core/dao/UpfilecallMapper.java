package com.ssm.promotion.core.dao;

import com.ssm.promotion.core.entity.Upfilecall;

import java.util.List;

public interface UpfilecallMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Upfilecall record);

    int insertSelective(Upfilecall record);

    Upfilecall selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Upfilecall record);

    int updateByPrimaryKey(Upfilecall record);

    Upfilecall selectByTypeFileName(String btype, String bfrealname);

    List<Upfilecall> selectByType(String bType);

}