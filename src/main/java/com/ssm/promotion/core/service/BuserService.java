package com.ssm.promotion.core.service;

import com.ssm.promotion.core.entity.Buser;

public interface BuserService {


    public int deleteByPrimaryKey(Integer id);

    public int insert(Buser record);

    public int insertSelective(Buser record);

    public Buser selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Buser record);

    public int updateByPrimaryKey(Buser record);

    public Buser selectBuserByPhone(String bUsePhone);

}
