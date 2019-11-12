package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.UpfilecallMapper;
import com.ssm.promotion.core.entity.Upfilecall;
import com.ssm.promotion.core.service.UpfilecallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("upfilecallService")
public class UpfilecallServiceImpl implements UpfilecallService {

    @Resource
    private UpfilecallMapper upfilecallMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return upfilecallMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Upfilecall record) {
        return upfilecallMapper.insert(record);
    }

    
    public int insertSelective(Upfilecall record) {
        return upfilecallMapper.insertSelective(record);
    }

    
    public Upfilecall selectByPrimaryKey(Integer id) {
        return upfilecallMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Upfilecall> selectByType(String bType) {

        return upfilecallMapper.selectByType(bType);
    }

    @Override
    public Upfilecall sUpfilecallByTypeFileName(String btype, String bfrealname) {
        return upfilecallMapper.selectByTypeFileName(btype,bfrealname);
    }


    public int updateByPrimaryKeySelective(Upfilecall record) {
        return upfilecallMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Upfilecall record) {
        return upfilecallMapper.updateByPrimaryKey(record);
    }

}
