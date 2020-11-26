package com.beifeng.service;

import com.beifeng.domain.Comment;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/17 22:38
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(String blogId);

    String saveComment(Comment comment);

    String editCommentById(String commentId, String editComment);

    String deleteCommentById(String commentId);
}
