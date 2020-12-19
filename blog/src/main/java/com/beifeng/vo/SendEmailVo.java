package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/12/19 21:15
 */
/*评论邮件通知*/
public class SendEmailVo {

    private String nickname;// 评论昵称
    private String content;// 评论内容

    private String blogName;// 评论对应的博客名称
    private String blogId;// 评论对应的博客Id

    private Boolean replyFlag;// 回复标记
    private String replyNickname;// 被回复的评论昵称
    private String replyContent;// 被回复的评论内容
    private String replyEmail;// 被回复的评论邮箱

    public SendEmailVo() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public Boolean getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Boolean replyFlag) {
        this.replyFlag = replyFlag;
    }

    public String getReplyNickname() {
        return replyNickname;
    }

    public void setReplyNickname(String replyNickname) {
        this.replyNickname = replyNickname;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyEmail() {
        return replyEmail;
    }

    public void setReplyEmail(String replyEmail) {
        this.replyEmail = replyEmail;
    }

    @Override
    public String toString() {
        return "SendEmailVo{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", blogName='" + blogName + '\'' +
                ", blogId='" + blogId + '\'' +
                ", replyFlag=" + replyFlag +
                ", replyNickname='" + replyNickname + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyEmail='" + replyEmail + '\'' +
                '}';
    }
}
