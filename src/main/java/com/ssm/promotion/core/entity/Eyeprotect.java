package com.ssm.promotion.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.JsonAdapter;
import com.ssm.promotion.core.util.DateAdapter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Eyeprotect implements Serializable {
    /**
     * 设备ID 设备ID
     */
    private String robotId;

    /**
     * 乐观锁
     */
    private int revision;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonAdapter(DateAdapter.class)
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonAdapter(DateAdapter.class)
    private Date updatedTime;

    /**
     * 护眼开关 0-关闭1-开启
     */
    private int eyeTip;

    /**
     * 工作日开始时间 工作日开始时间
     */
    private int timeBegin;

    /**
     * 工作日结束时间
     */
    private int timeEnd;

    /**
     * 工作日使用时长
     */
    private int timeLength;

    /**
     * 工作日休息时长
     */
    private int timeLimit;

    /**
     * 双休日开始时间
     */
    private int workTimeBegin;

    /**
     * 双休日结束时间
     */
    private int workTimeEng;

    /**
     * 双休日使用时长
     */
    private int workTimeLength;

    /**
     * 双休日休息时长
     */
    private int workTimeLimit;

    private static final long serialVersionUID = 1L;


    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public int getEyeTip() {
        return eyeTip;
    }

    public void setEyeTip(int eyeTip) {
        this.eyeTip = eyeTip;
    }

    public int getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(int timeBegin) {
        this.timeBegin = timeBegin;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getWorkTimeBegin() {
        return workTimeBegin;
    }

    public void setWorkTimeBegin(int workTimeBegin) {
        this.workTimeBegin = workTimeBegin;
    }

    public int getWorkTimeEng() {
        return workTimeEng;
    }

    public void setWorkTimeEng(int workTimeEng) {
        this.workTimeEng = workTimeEng;
    }

    public int getWorkTimeLength() {
        return workTimeLength;
    }

    public void setWorkTimeLength(int workTimeLength) {
        this.workTimeLength = workTimeLength;
    }

    public int getWorkTimeLimit() {
        return workTimeLimit;
    }

    public void setWorkTimeLimit(int workTimeLimit) {
        this.workTimeLimit = workTimeLimit;
    }
}