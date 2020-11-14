package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/12 21:51
 */

/*博客和标签多对多关联*/
public class BlogAndTagVo {

    private String id;
    private String blogId;
    private String tagId;

    public BlogAndTagVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "BlogAndTag{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
