package com.ssm.promotion.core.service.impl;

import com.ssm.promotion.core.dao.ChatMapper;
import com.ssm.promotion.core.entity.Chat;
import com.ssm.promotion.core.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    @Resource
    private ChatMapper chatMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return chatMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Chat record) {
        return chatMapper.insert(record);
    }

    
    public int insertSelective(Chat record) {
        return chatMapper.insertSelective(record);
    }

    
    public Chat selectByPrimaryKey(Integer id) {
        return chatMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Chat record) {
        return chatMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Chat record) {
        return chatMapper.updateByPrimaryKey(record);
    }

}
