package com.beifeng.web.controller;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/10/26 16:09
 */
import com.beifeng.domain.Comment;
import com.beifeng.service.BlogService;
import com.beifeng.service.CommentService;
import com.beifeng.service.TagService;
import com.beifeng.service.TypeService;
import com.beifeng.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam (defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("进入到博客首页操作");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> allFirstPageBlog = blogService.getIndexBlogs();
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(allFirstPageBlog);

        List<TypeVo> IndexTypes = typeService.getTypesVo();
        List<TagVo> IndexTags = tagService.getTagsVo();
        List<BlogVo> LatestRecommendedBlog = blogService.getLatestRecommendedBlogs();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", IndexTypes);
        model.addAttribute("tags", IndexTags);
        model.addAttribute("RecommendedBlogs", LatestRecommendedBlog);

        return "index";
    }


    @PostMapping("/")
    public String indexPage(Model model,
                        @RequestParam (defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("执行博客首页分页操作");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> allFirstPageBlog = blogService.getIndexBlogs();
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(allFirstPageBlog);

        model.addAttribute("pageInfo", pageInfo);

        return "index :: indexBlogList";
    }


    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam String query,
                         Model model){
        System.out.println("执行博客查询");


        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> searchBlog = blogService.getBlogsByQuery(query.trim());

        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);

        return "search";

    }

    @PostMapping("/search")
    public String searchPage(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         @RequestParam String query,
                         Model model){
        System.out.println("执行博客查询分页操作");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> searchBlog = blogService.getBlogsByQuery(query.trim());

        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);

        return "search :: searchBlogList";

    }


    @GetMapping("/blog/{id}")
    public String blog(@PathVariable String id,Model model){
        DetailedBlogVo detailedBlog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);

        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);

        return "blog";
    }

    @GetMapping("/footer/blogInfo")
    public String blogInfo(Model model){
        System.out.println("获取博客信息统计");
        BlogInfoVo blogInfoVo = blogService.getBlogInfo();
        model.addAttribute("blogInfo", blogInfoVo);

        return "_fragments :: blogInfo";
    }

}
