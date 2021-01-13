package com.beifeng.vo;

import com.beifeng.domain.Tag;
import com.beifeng.domain.Type;

import java.util.Date;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/4 20:46
 */

/*列表展示blog*/
public class BlogVo {

    private String id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Boolean sticky;
    private String typeId;

    private Type type;

    public BlogVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BlogVo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", updateTime=" + updateTime +
                ", recommend=" + recommend +
                ", published=" + published +
                ", sticky=" + sticky +
                ", typeId='" + typeId + '\'' +
                ", type=" + type +
                '}';
    }
}
