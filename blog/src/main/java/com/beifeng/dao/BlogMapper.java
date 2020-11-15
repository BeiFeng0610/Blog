package com.beifeng.dao;


import com.beifeng.domain.Blog;
import com.beifeng.vo.BlogAndTagVo;
import com.beifeng.vo.BlogVo;
import com.beifeng.vo.SearchBlogVo;

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
}