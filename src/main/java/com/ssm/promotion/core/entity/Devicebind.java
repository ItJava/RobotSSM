package com.ssm.promotion.core.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Devicebind {
    /**
    * ID
    */
    private Integer id;

    /**
    * 用户ID
    */
    private String phone;

    /**
    * 设备ID
    */
    private String deviceId;

    /**
    * 绑定时间时间
    */
    private Date bindTime;
}