package com.beifeng.web.controller;

import com.beifeng.service.BlogService;
import com.beifeng.service.TagService;
import com.beifeng.vo.IndexBlogsVo;
import com.beifeng.vo.TagVo;
import com.beifeng.vo.TypeVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/19 19:44
 */
@Controller
public class TagShowController {

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @GetMapping("/tags")
    public String tags(Model model,
                       @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("进入标签页面操作");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> tagsPageBlog = blogService.getIndexBlogs();
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(tagsPageBlog);

        List<TagVo> tags = tagService.getAllTagVos();
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);

        return "tags";
    }

    @GetMapping("/tags/{id}")
    public String blogsByTypeId(@PathVariable String id,
                                Model model,
                                @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("执行根据标签获取博客");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> blogsByTagId = blogService.getBlogsByTagId(id);
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(blogsByTagId);

        List<TagVo> tags = tagService.getAllTagVos();
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("selectId", id);

        return "tags";
    }
}
