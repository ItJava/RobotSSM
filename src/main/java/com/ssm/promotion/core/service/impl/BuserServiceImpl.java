package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.BuserMapper;
import com.ssm.promotion.core.entity.Buser;
import com.ssm.promotion.core.service.BuserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("buserService")
public class BuserServiceImpl implements BuserService {

    @Resource
    private BuserMapper buserMapper;


    public int deleteByPrimaryKey(Integer id) {
        return buserMapper.deleteByPrimaryKey(id);
    }


    public int insert(Buser record) {
        return buserMapper.insert(record);
    }


    public int insertSelective(Buser record) {
        return buserMapper.insertSelective(record);
    }


    public Buser selectByPrimaryKey(Integer id) {
        return buserMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Buser record) {
        return buserMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Buser record) {
        return buserMapper.updateByPrimaryKey(record);
    }

    @Override
    public Buser selectBuserByPhone(String bUsePhone) {
        return buserMapper.selectBuserByPhone(bUsePhone);
    }




}
