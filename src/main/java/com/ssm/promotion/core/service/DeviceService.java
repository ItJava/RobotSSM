package com.ssm.promotion.core.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ssm.promotion.core.dao.DeviceMapper;
import com.ssm.promotion.core.entity.Device;

public interface DeviceService{




    public int deleteByPrimaryKey(String deviceId);

    public int insert(Device record);


    public int insertSelective(Device record);


    public Device selectByPrimaryKey(String deviceId) ;


    public int updateByPrimaryKeySelective(Device record) ;

    public int updateByPrimaryKey(Device record) ;
}
