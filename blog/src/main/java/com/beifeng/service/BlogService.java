package com.beifeng.service;

import com.beifeng.domain.Blog;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.vo.BlogVo;
import com.beifeng.vo.DetailedBlogVo;
import com.beifeng.vo.IndexBlogsVo;
import com.beifeng.vo.SearchBlogVo;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/4 21:15
 */
public interface BlogService {

    List<BlogVo> getAllBlog();

    String saveBlog(Blog blog,String tagIds);

    List<BlogVo> getBlogBySearch(SearchBlogVo searchBlogVo);

    Blog getBlogById(String id);

    String getTagsById(String id);

    String updateBlog(Blog blog, String tagIds);

    String deleteBlog(String id);

    List<IndexBlogsVo> getIndexBlogs();

    List<BlogVo> getLatestRecommendedBlogs();

    List<IndexBlogsVo> getBlogsByQuery(String query);

    DetailedBlogVo getDetailedBlog(String id);

    List<IndexBlogsVo> getBlogsByTypeId(String id);

    List<IndexBlogsVo> getBlogsByTagId(String id);

    Integer getBlogCount();
}
