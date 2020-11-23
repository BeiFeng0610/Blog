package com.beifeng.dao;


import com.beifeng.domain.Blog;
import com.beifeng.vo.*;

import java.util.List;

public interface BlogMapper {

    List<BlogVo> getAllBlogVo();

    List<BlogVo> searchByTitleOrTypeOrRecommend(SearchBlogVo searchBlogVo);

    Integer saveBlogAndTags(BlogAndTagVo blogAndTag);

    Integer saveBlog(Blog blog);

    Blog getBlogById(String id);

    List<String> getTagsById(String id);

    Integer deleteBlogAndTags(String id);

    Integer updateBlog(Blog blog);

    Integer deleteBlog(String id);

    List<IndexBlogsVo> getIndexBlogList();

    List<BlogVo> getLatestRecommendedBlogList();

    List<IndexBlogsVo> getBlogByQueryList(String query);

    DetailedBlogVo getDetailedBlog(String id);

    void updateViewAddOne(Integer views,String id);

    List<IndexBlogsVo> getBlogVoByTypeIdList(String typeId);

    List<IndexBlogsVo> getBlogVoByTagIdList(String tagId);

    Integer getBlogCount();
}