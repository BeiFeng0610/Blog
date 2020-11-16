package com.beifeng.service.impl;

import com.beifeng.dao.BlogMapper;
import com.beifeng.dao.TagMapper;
import com.beifeng.domain.Blog;
import com.beifeng.domain.Tag;
import com.beifeng.execption.NotFoundException;
import com.beifeng.service.BlogService;
import com.beifeng.util.DateTimeUtil;
import com.beifeng.util.MarkdownUtils;
import com.beifeng.util.UUIDUtil;
import com.beifeng.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private TagMapper tagMapper;

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

    @Override
    public List<IndexBlogsVo> getIndexBlogs() {

        List<IndexBlogsVo> indexBlogList = blogMapper.getIndexBlogList();

        List<IndexBlogsVo> blogList = setTags(indexBlogList);

        return blogList;

    }

    @Override
    public List<BlogVo> getLatestRecommendedBlogs() {
        return blogMapper.getLatestRecommendedBlogList();
    }


    @Override
    public List<IndexBlogsVo> getBlogsByQuery(String query) {

        List<IndexBlogsVo> blogList = blogMapper.getBlogByQueryList(query);
        List<IndexBlogsVo> BlogByQueryList = setTags(blogList);

        return BlogByQueryList;
    }

    @Override
    public DetailedBlogVo getDetailedBlog(String id) {
        DetailedBlogVo detailedBlog = blogMapper.getDetailedBlog(id);
        if (null==detailedBlog){
            throw new NotFoundException("该博客不存在");
        }

        List<String> tagIds = blogMapper.getTagsById(id);
        List<Tag> tags = new ArrayList<>();
        for (String tagId:tagIds){
            Tag tag = tagMapper.getTagById(tagId);
            tags.add(tag);
        }
        detailedBlog.setTags(tags);

        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        Integer views = detailedBlog.getViews() + 1;
        blogMapper.updateViewAddOne(views,id);
        detailedBlog.setViews(views);

        return detailedBlog;
    }

    // 拆分ids
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

    // 设置tags
    private List<IndexBlogsVo> setTags(List<IndexBlogsVo> blogList){
        for (IndexBlogsVo blog : blogList){
            List<Tag> tags = new ArrayList<>();
            String blogId = blog.getId();
            List<String> tagIds = blogMapper.getTagsById(blogId);
            for (String tagId:tagIds){
                Tag tag = tagMapper.getTagById(tagId);
                tags.add(tag);
            }
            blog.setTags(tags);
        }

        return blogList;
    }
}
