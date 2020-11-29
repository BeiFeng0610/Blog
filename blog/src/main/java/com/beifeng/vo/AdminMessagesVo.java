package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/30 3:42
 */
public class AdminMessagesVo {

    private String id;
    private String nickname;
    private boolean type;
    private String createTime;
    private String content;

    public AdminMessagesVo() {
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

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AdminMessagesVo{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
