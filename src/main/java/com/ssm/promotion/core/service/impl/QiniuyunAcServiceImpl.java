package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.QiniuyunAcMapper;
import com.ssm.promotion.core.entity.QiniuyunAc;
import com.ssm.promotion.core.service.QiniuyunAcService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("qiniuyun")
public class QiniuyunAcServiceImpl implements QiniuyunAcService {

    @Resource
    private QiniuyunAcMapper qiniuyunAcMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return qiniuyunAcMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(QiniuyunAc record) {
        return qiniuyunAcMapper.insert(record);
    }

    
    public int insertSelective(QiniuyunAc record) {
        return qiniuyunAcMapper.insertSelective(record);
    }

    
    public QiniuyunAc selectByPrimaryKey(int id) {
        return qiniuyunAcMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(QiniuyunAc record) {
        return qiniuyunAcMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(QiniuyunAc record) {
        return qiniuyunAcMapper.updateByPrimaryKey(record);
    }

}
