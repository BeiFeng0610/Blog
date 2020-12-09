package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/12/10 3:35
 */
/*博客信息统计*/
public class BlogInfoVo {

    private Integer views;
    private Integer commentCount;
    private Integer messageCount;

    public BlogInfoVo() {
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    @Override
    public String toString() {
        return "BlogInfoVo{" +
                "views=" + views +
                ", commentCount=" + commentCount +
                ", messageCount=" + messageCount +
                '}';
    }
}
