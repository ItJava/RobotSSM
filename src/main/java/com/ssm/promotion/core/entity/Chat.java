package com.ssm.promotion.core.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Chat {
    /**
     * 对话ID
     */
    private Integer id;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 对话时间
     */
    private Date chatTime;

    /**
     * 对话问题
     */
    private String chatQuestion;

    /**
     * 对话答案
     */
    private String chatAnswer;

    /**
     * 对话类型 聊天功能分布：聊天、视频播放、百科、视频播放、谁在叫、成语接龙、笑话、绕口令、顺口溜
     */
    private String chatType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }

    public String getChatQuestion() {
        return chatQuestion;
    }

    public void setChatQuestion(String chatQuestion) {
        this.chatQuestion = chatQuestion;
    }

    public String getChatAnswer() {
        return chatAnswer;
    }

    public void setChatAnswer(String chatAnswer) {
        this.chatAnswer = chatAnswer;
    }

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }
}