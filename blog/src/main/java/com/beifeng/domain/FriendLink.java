package com.beifeng.domain;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/29 21:12
 */
// 友链实体类
public class FriendLink {

    private String id;
    private String blogName;
    private String blogLink;
    private String avatarLink;
    private String createTime;

    public FriendLink() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FriendLink{" +
                "id='" + id + '\'' +
                ", blogName='" + blogName + '\'' +
                ", blogLink='" + blogLink + '\'' +
                ", avatarLink='" + avatarLink + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
