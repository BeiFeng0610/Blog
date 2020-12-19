package com.beifeng.web.admin.controller;

import com.beifeng.domain.Tag;
import com.beifeng.domain.Type;
import com.beifeng.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/3 22:57
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Model model){

        System.out.println("进入标签页面操作");
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10,orderBy);
        List<Tag> list = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/tags";

    }

    @PostMapping("/tags/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Model model){

        System.out.println("执行标签动态分页操作");
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10,orderBy);
        List<Tag> list = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/tags :: tagList";

    }

    @GetMapping("/tags/input")
    public String saveInput(Model model){

        System.out.println("进入标签添加页面");

        model.addAttribute("tag", new Tag()); //返回一个tag对象给前端th:object
        return "admin/tags-input";
    }

    /*添加校验*/
    @PostMapping("/tags")
    public String saveTag(@Valid Tag tag,BindingResult result, RedirectAttributes attributes){

        System.out.println("执行标签添加数据校验");

        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }else {
            System.out.println("添加标签");
            tagService.saveTag(tag);
            attributes.addFlashAttribute("msg", "添加成功");
        }

        return "redirect:/admin/tags";
    }

    /*修改类型*/
    @GetMapping("/tags/input/{id}")
    public String updateInput(@PathVariable String id, Model model){
        System.out.println("进入标签修改操作");

        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    /*编辑修改分类*/
    @PostMapping("/tags/{id}")
    public String updateTag(@Valid Tag tag, BindingResult result, String id, RedirectAttributes attributes){
        System.out.println("执行标签修改数据校验");

        if (result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复的分类");
            return "redirect:/admin/tags/input";
        }

        String msg = tagService.updateTag(tag.getName(),id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/tags";
    }

    /*删除分类*/
    @GetMapping("/tags/delete/{id}")
    public String deleteTag(@PathVariable String id,RedirectAttributes attributes){
        System.out.println("执行标签删除操作");

        String msg = tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/tags";
    }
}
