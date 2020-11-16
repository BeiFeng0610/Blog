package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/16 16:39
 */
public class TypeVo {

    private String id;
    private String name;
    private Integer blogCount;

    public TypeVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    @Override
    public String toString() {
        return "TypesVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", blogCount=" + blogCount +
                '}';
    }
}
