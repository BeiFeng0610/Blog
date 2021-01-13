package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/7 19:15
 */

/*博客条件查询*/
public class SearchBlogVo {

    private String title;
    private String typeId;
    private Boolean recommend;
    private Boolean sticky;

    public SearchBlogVo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    @Override
    public String toString() {
        return "SearchBlogVo{" +
                "title='" + title + '\'' +
                ", typeId='" + typeId + '\'' +
                ", recommend=" + recommend +
                ", sticky=" + sticky +
                '}';
    }
}
