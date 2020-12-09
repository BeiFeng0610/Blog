package com.beifeng.dao;

import com.beifeng.domain.Message;
import com.beifeng.vo.AdminMessagesVo;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/30 0:45
 */
public interface MessageMapper {

    List<Message> getParentMessages();

    List<Message> getSubMessagesByParentId(String parentId);

    Message getReplyMessageByReplyMessageId(String replyMessageId);

    Integer saveMessage(Message message);

    Integer deleteMessageById(String messageId);

    List<AdminMessagesVo> getMessagesByDESC();

    Integer getMessageCount();
}
