package com.beifeng.web.admin.controller;

import com.beifeng.domain.Blog;
import com.beifeng.domain.Type;
import com.beifeng.domain.User;
import com.beifeng.service.BlogService;
import com.beifeng.service.TagService;
import com.beifeng.service.TypeService;
import com.beifeng.vo.BlogVo;
import com.beifeng.vo.SearchBlogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 23:25
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    /*private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "admin/blogs-input";*/

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        System.out.println("进入博客页面操作");
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";

        PageHelper.startPage(pageNum, 10,orderBy);
        List<BlogVo> list = blogService.getAllBlog();

        PageInfo<BlogVo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("types", typeService.getAllType());// 查询类型和标签

        return "admin/blogs";

    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                         SearchBlogVo searchBlogVo,Model model){
        System.out.println("执行条件查询和分页操作");
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";

        PageHelper.startPage(pageNum, 10,orderBy);
        List<BlogVo> blogBySearch = blogService.getBlogBySearch(searchBlogVo);

        PageInfo<BlogVo> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/blogs :: blogList";

    }

    @GetMapping("/blogs/input")
    public String saveInput(Model model){
        System.out.println("进入到博客添加操作");

        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String saveBlog(@Valid Blog blog, BindingResult result,
                           @RequestParam String tagIds,
                           RedirectAttributes attributes,
                           HttpSession session,
                           Model model){
        System.out.println("执行博客添加数据校验");

        if (result.hasErrors()){
            setTypeAndTag(model);
            return "admin/blogs-input";
        }

        User user = ((User) session.getAttribute("user"));
        blog.setUserId(user.getId());

        String msg = blogService.saveBlog(blog,tagIds);

        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/update/{id}")
    public String updateInput(@PathVariable String id,
                              Model model){
        System.out.println("进入博客更新操作");

        setTypeAndTag(model);
        model.addAttribute("blog",blogService.getBlogById(id));
        model.addAttribute("tagIds", blogService.getTagsById(id));

        return "admin/blogs-update";
    }

    @PostMapping("/blogs/update")
    public String updateBlog(@Valid Blog blog, BindingResult result,
                             @RequestParam String tagIds,
                             RedirectAttributes attributes,
                             Model model){

        System.out.println("执行博客更新数据校验");

        if (result.hasErrors()){
            setTypeAndTag(model);
            return "admin/blogs-input";
        }

        String msg = blogService.updateBlog(blog,tagIds);

        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable String id,
                             RedirectAttributes attributes){
        System.out.println("执行删除博客操作");

        String msg = blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/blogs";
    }

}
