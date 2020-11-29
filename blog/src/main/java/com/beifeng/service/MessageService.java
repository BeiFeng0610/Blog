package com.beifeng.service;

import com.beifeng.domain.Message;
import com.beifeng.vo.AdminMessagesVo;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/30 0:42
 */
public interface MessageService {

    List<Message> getMessages();

    String saveMessage(Message message);

    String deleteMessageById(String messageId);

    // 获取最新留言
    List<AdminMessagesVo> getMessagesByDESC();
}
