package com.beifeng.web.controller;

import com.beifeng.service.BlogService;
import com.beifeng.service.TypeService;
import com.beifeng.vo.IndexBlogsVo;
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
 * @date 2020/11/19 18:27
 */
@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @GetMapping("/types")
    public String types(Model model,
                        @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("进入分类页面操作");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> typesPageBlog = blogService.getIndexBlogs();
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(typesPageBlog);

        List<TypeVo> types = typeService.getAllTypesVo();
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);

        return "types";
    }

    @GetMapping("/types/{id}")
    public String blogsByTypeId(@PathVariable String id,
                              Model model,
                              @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        System.out.println("执行根据分类获取博客");

        PageHelper.startPage(pageNum, 10);
        List<IndexBlogsVo> blogsByTypeId = blogService.getBlogsByTypeId(id);
        PageInfo<IndexBlogsVo> pageInfo = new PageInfo<>(blogsByTypeId);

        List<TypeVo> types = typeService.getAllTypesVo();
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("selectId", id);

        return "types";
    }
}
