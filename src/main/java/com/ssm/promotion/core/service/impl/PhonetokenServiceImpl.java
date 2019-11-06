package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.PhonetokenMapper;
import com.ssm.promotion.core.entity.Phonetoken;
import com.ssm.promotion.core.service.PhonetokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("phonetokenService")
public class PhonetokenServiceImpl implements PhonetokenService {

    @Resource
    private PhonetokenMapper phonetokenMapper;


    public int deleteByPrimaryKey(Integer id) {
        return phonetokenMapper.deleteByPrimaryKey(id);
    }


    public int insert(Phonetoken record) {
        return phonetokenMapper.insert(record);
    }


    public int insertSelective(Phonetoken record) {
        return phonetokenMapper.insertSelective(record);
    }


    public Phonetoken selectByPrimaryKey(Integer id) {
        return phonetokenMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Phonetoken record) {
        return phonetokenMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Phonetoken record) {
        return phonetokenMapper.updateByPrimaryKey(record);
    }


}
