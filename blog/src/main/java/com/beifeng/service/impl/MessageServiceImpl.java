package com.beifeng.service.impl;

import com.beifeng.dao.MessageMapper;
import com.beifeng.domain.Comment;
import com.beifeng.domain.Message;
import com.beifeng.service.MessageService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
import com.beifeng.vo.AdminMessagesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/30 0:43
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getMessages() {
        //存放找出的所有超级父评论下的回复
        List<Message> tempReplys = new ArrayList<>();
        // 获取超级父评论
        List<Message> messages = messageMapper.getParentMessages();
        // 遍历超级父评论,获取所有的回复评论
        for (Message message : messages){
            String parentId = message.getId();
            List<Message> subMessages = messageMapper.getSubMessagesByParentId(parentId);
            if (subMessages.size()>0){
                setReplyCommend(subMessages,tempReplys);
                message.setReplyComments(tempReplys);
                tempReplys = new ArrayList<>();
            }
        }

        return messages;
    }

    @Transactional
    @Override
    public String saveMessage(Message message) {
        message.setId(UUIDUtil.getUUID());
        message.setCreateTime(DateTimeUtil.getSysTime());

        String msg = "评论失败";

        Integer count = messageMapper.saveMessage(message);
        if (count==1){
            msg = "评论成功";
        }

        return msg;
    }

    @Transactional
    @Override
    public String deleteMessageById(String messageId) {
        String msg = "删除失败";
        Integer count = messageMapper.deleteMessageById(messageId);
        if (count>0){
            msg = "删除成功";
        }
        return msg;
    }

    @Override
    public List<AdminMessagesVo> getMessagesByDESC() {
        // 获取所有留言  按照最新排序
        List<AdminMessagesVo> messages = messageMapper.getMessagesByDESC();

        return messages;
    }

    /*判断子回复是否在超级父评论下回复过其他  子回复*/
    private void setReplyCommend(List<Message> subMessages,List<Message> tempReplys){
        if (subMessages.size()>0){
            for (Message subMessage:subMessages){
                // 获取该子评论是否回复过其他子评论
                // 如果为null 或者 为空字符串，表示没有回复过其他子评论,则不再为其设置子评论对象
                String replyMessageId = subMessage.getReplyCommentId();
                if (replyMessageId!=null || replyMessageId!=""){
                    // 根据此id设置查询 回复的对象
                    Message replyCommend = messageMapper.getReplyMessageByReplyMessageId(replyMessageId);
                    subMessage.setReplyComment(replyCommend);
                    tempReplys.add(subMessage);
                }

            }
        }

    }
}
