package com.beifeng.dao;

import com.beifeng.domain.Comment;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/18 19:07
 */
public interface CommentMapper {

    /*通过博客id查询超级父评论*/
    List<Comment> getParentCommentListByBlogId(String blogId);

    /*通过顶级评论id查询超级父评论下的所有评论*/
    List<Comment> getSubCommentsByParentId(String parentId);

    /*通过回复id查询  子评论对象*/
    Comment getReplyCommendByReplyCommendId(String replyCommendId);

    Integer saveComment(Comment comment);
}
