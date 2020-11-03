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
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){

        System.out.println("进入标签页面");
        // 按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 5,orderBy);
        List<Tag> list = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/tags";

    }

    @GetMapping("/tags/input")
    public String tagsInput(Model model){

        System.out.println("进入标签添加页面");

        model.addAttribute("tag", new Tag()); //返回一个tag对象给前端th:object
        return "admin/tags-input";
    }

    /*添加校验*/
    @PostMapping("/tags")
    public String postTags(@Valid Tag tag,BindingResult result, RedirectAttributes attributes){

        System.out.println("进入标签校验添加");

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
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        System.out.println("进入标签修改界面");

        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    /*编辑修改分类*/
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,Long id, RedirectAttributes attributes){
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
    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){

        String msg = tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/tags";
    }
}
