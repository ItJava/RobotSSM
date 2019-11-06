package com.ssm.promotion.core.entity;

import java.util.Date;

public class Phonetoken {
    /**
    * TOKEN_ID
    */
    private Integer id;

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
    * 设备ID
    */
    private String deviceId;

    /**
    * 音视频TOKEN
    */
    private String phoneToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneToken() {
        return phoneToken;
    }

    public void setPhoneToken(String phoneToken) {
        this.phoneToken = phoneToken;
    }
}