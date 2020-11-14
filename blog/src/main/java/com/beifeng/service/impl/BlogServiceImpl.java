package com.beifeng.service.impl;

import com.beifeng.dao.BlogMapper;
import com.beifeng.domain.Blog;
import com.beifeng.domain.Tag;
import com.beifeng.service.BlogService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.UUIDUtil;
import com.beifeng.vo.BlogAndTagVo;
import com.beifeng.vo.BlogVo;
import com.beifeng.vo.SearchBlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/4 21:17
 */
@Transactional
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public List<BlogVo> getAllBlog() {

        List<BlogVo> blogVoList = blogMapper.getAllBlogVo();

        return blogVoList;
    }

    @Override
    public String saveBlog(Blog blog,String tagIds) {
        /*标记*/
        String msg = "添加博客失败";
        String blogId = UUIDUtil.getUUID();
        blog.setId(blogId);
        blog.setViews(0);
        blog.setCreateTime(DateTimeUtil.getSysTime());
        blog.setUpdateTime(DateTimeUtil.getSysTime());

        if (blog.getShareStatement()==null){
            blog.setShareStatement(false);
        }
        if (blog.getCommentabled()==null){
            blog.setCommentabled(false);
        }
        if (blog.getRecommend()==null){
            blog.setRecommend(false);
        }

        //将标签的数据存到t_blogs_tag表中
        BlogAndTagVo blogAndTag = null;

        List<String> ids = convertToList(tagIds);
        for (String id : ids){
            blogAndTag = new BlogAndTagVo();
            blogAndTag.setId(UUIDUtil.getUUID());
            blogAndTag.setBlogId(blogId);
            blogAndTag.setTagId(id);
            Integer count = blogMapper.saveBlogAndTags(blogAndTag);
            if (count==1){
                msg = "添加博客成功";
            }
        }
        Integer count = blogMapper.saveBlog(blog);
        if (count==1){
            msg = "添加博客成功";
        }

        return msg;
    }

    @Override
    public Blog getBlogById(String id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<BlogVo> getBlogBySearch(SearchBlogVo searchBlogVo) {

        List<BlogVo> blogVoList = blogMapper.searchByTitleOrTypeOrRecommend(searchBlogVo);
        return blogVoList;
    }

    @Override
    public String getTagsById(String id) {
        List<String> tags = blogMapper.getTagsById(id);

        String tagIds="";
        for (String tagId :tags){
            tagIds += tagId+",";
        }

        if (tagIds.length()>0){
            tagIds = tagIds.substring(0, tagIds.length() - 1);
        }

        System.out.println(tagIds);
        return tagIds;
    }

    @Override
    public String updateBlog(Blog blog, String tagIds) {
        /*标记*/
        String msg = "更新博客失败";
        blog.setUpdateTime(DateTimeUtil.getSysTime());

        if (blog.getShareStatement()==null){
            blog.setShareStatement(false);
        }
        if (blog.getCommentabled()==null){
            blog.setCommentabled(false);
        }
        if (blog.getRecommend()==null){
            blog.setRecommend(false);
        }

        blogMapper.deleteBlogAndTags(blog.getId());

        //删除原有的博客与标签的关联
        if (tagIds!=null && tagIds!=""){

            //将标签的数据存到t_blogs_tag表中
            BlogAndTagVo blogAndTag = null;

            List<String> ids = convertToList(tagIds);
            for (String id : ids){
                blogAndTag = new BlogAndTagVo();
                blogAndTag.setId(UUIDUtil.getUUID());
                blogAndTag.setBlogId(blog.getId());
                blogAndTag.setTagId(id);
                Integer count = blogMapper.saveBlogAndTags(blogAndTag);
                if (count==1){
                    msg = "更新博客失败";
                }
            }

        }


        Integer count = blogMapper.updateBlog(blog);
        if (count==1){
            msg = "更新博客成功";
        }

        return msg;
    }

    @Override
    public String deleteBlog(String id) {
        //删除博客与标签的关联

        String msg = "删除博客失败";
        blogMapper.deleteBlogAndTags(id);
        Integer count = blogMapper.deleteBlog(id);
        if (count==1){
            msg = "删除博客成功";
        }

        return msg;
    }

    private List<String> convertToList(String ids){
        List<String> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idArray = ids.split(",");
            for (int i=0; i < idArray.length;i++) {
                list.add(String.valueOf(idArray[i]));
            }
        }
        return list;
    }

}
