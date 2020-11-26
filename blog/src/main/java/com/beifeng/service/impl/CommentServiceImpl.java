package com.beifeng.service.impl;

import com.beifeng.dao.CommentMapper;
import com.beifeng.domain.Comment;
import com.beifeng.service.CommentService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/17 22:40
 */
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public String saveComment(Comment comment) {
        comment.setId(UUIDUtil.getUUID());
        comment.setCreateTime(DateTimeUtil.getSysTime());

        String msg = "评论失败";

        Integer count = commentMapper.saveComment(comment);
        if (count==1){
            msg = "评论成功";
        }

        return msg;
    }

    @Override
    public List<Comment> listCommentByBlogId(String blogId) {
        //存放找出的所有超级父评论下的回复
        List<Comment> tempReplys = new ArrayList<>();
        // 获取超级父评论
        List<Comment> comments = commentMapper.getParentCommentListByBlogId(blogId);
        // 遍历超级父评论,获取所有的回复评论
        for (Comment comment : comments){
            String parentId = comment.getId();
            List<Comment> subComments = commentMapper.getSubCommentsByParentId(parentId);
            if (subComments.size()>0){
                setReplyCommend(subComments,tempReplys);
                comment.setReplyComments(tempReplys);
                tempReplys = new ArrayList<>();
            }
        }

        return comments;
    }

    @Override
    public String editCommentById(String commentId, String content) {
        String msg = "修改失败";
        Integer count = commentMapper.editCommentById(commentId,content);
        if (count==1){
            msg = "修改成功";
        }
        return msg;
    }

    @Override
    public String deleteCommentById(String commentId) {
        String msg = "删除失败";
        Integer count = commentMapper.deleteCommentById(commentId);
        if (count>0){
            msg = "删除成功";
        }
        return msg;
    }

    /*判断子回复是否在超级父评论下回复过其他  子回复*/
    private void setReplyCommend(List<Comment> subComments,List<Comment> tempReplys){
        if (subComments.size()>0){
            for (Comment subComment:subComments){
                // 获取该子评论是否回复过其他子评论
                // 如果为null 或者 为空字符串，表示没有回复过其他子评论,则不再为其设置子评论对象
                String replyCommentId = subComment.getReplyCommentId();
                if (replyCommentId!=null || replyCommentId!=""){
                    // 根据此id设置查询 回复的对象
                    Comment replyCommend = commentMapper.getReplyCommendByReplyCommendId(replyCommentId);
                    subComment.setReplyComment(replyCommend);
                    tempReplys.add(subComment);
                }

            }
        }

    }

}
