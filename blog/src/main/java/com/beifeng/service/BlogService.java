package com.beifeng.service;

import com.beifeng.domain.Blog;
import com.beifeng.domain.Tag;
import com.beifeng.vo.BlogVo;
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
}
