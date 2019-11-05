package com.ssm.promotion.core.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ssm.promotion.core.entity.Devicebind;
import com.ssm.promotion.core.dao.DevicebindMapper;

public interface DevicebindService{



    
      int deleteByPrimaryKey(Integer id);

    
      int insert(Devicebind record);
    
      int insertSelective(Devicebind record);

    
      Devicebind selectByPrimaryKey(Integer id);
    
      int updateByPrimaryKeySelective(Devicebind record);

    
      int updateByPrimaryKey(Devicebind record);

}
