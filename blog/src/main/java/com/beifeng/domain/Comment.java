package com.beifeng.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/10/29 18:08
 */

public class Comment {

    private String id;

    private String nickname; // 昵称
    private String email; // 邮箱
    private String content; // 评论内容
    private String avatar; // 头像
    private String createTime; // 评论时间
    private String blogId; // 对应的博客id

    // 回复对应评论的id
    private String replyCommentId;

    // 对应超级父评论的id
    private String parentCommentId;

    // 所有在超级父类下的评论
    private List<Comment> replyComments = new ArrayList<>();

    // 对应的父评论对象
    private Comment replyComment;

    // 是否为管理员评论
    private Boolean adminComment;

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(String replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }

    public Comment getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }

    public Boolean getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Boolean adminComment) {
        this.adminComment = adminComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", blogId='" + blogId + '\'' +
                ", replyCommentId='" + replyCommentId + '\'' +
                ", parentCommentId='" + parentCommentId + '\'' +
                ", replyComments=" + replyComments +
                ", replyComment=" + replyComment +
                ", adminComment=" + adminComment +
                '}';
    }
}
