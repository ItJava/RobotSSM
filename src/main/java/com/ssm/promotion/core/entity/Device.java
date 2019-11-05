package com.ssm.promotion.core.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Device {
    /**
    * 设备ID
    */
    private String deviceId;

    /**
    * 乐观锁
    */
    private Integer revision;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    private Date createdTime;

    /**
    * 更新人
    */
    private String updatedBy;

    /**
    * 更新时间
    */
    private Date updatedTime;

    /**
    * 设备名称
    */
    private String deviceName;

    /**
    * 设备图像
    */
    private String devicePic;
}