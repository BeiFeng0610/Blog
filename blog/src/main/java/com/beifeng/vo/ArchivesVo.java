package com.beifeng.vo;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/23 23:37
 */
/*归档展示类*/
public class ArchivesVo {

    private String id;
    private String title;
    private String createTime;
    private String flag;

    public ArchivesVo() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ArchivesVo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
