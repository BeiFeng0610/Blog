package com.beifeng.web.admin.controller;

import com.beifeng.domain.Type;
import com.beifeng.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/2 16:46
 */
@Configuration
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    /*分页展示类型*/
    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){

        // 按照排序字段 倒序 排序
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 5,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/types";

    }

    /*添加类型*/
    @GetMapping("/types/input")
    public String typeInput(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /*添加类型校验*/
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.getTypeByName(type.getName());

        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            typeService.saveType(type);
            attributes.addFlashAttribute("msg", "添加成功");
        }

        return "redirect:/admin/types";
    }

    /*修改类型*/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /*编辑修改分类*/
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,Long id, RedirectAttributes attributes){
        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type t = typeService.getTypeByName(type.getName());
        if (t != null){
            attributes.addFlashAttribute("msg","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }

        String msg = typeService.updateType(type.getName(),id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/types";
    }

    /*删除分类*/
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id,RedirectAttributes attributes){

        String msg = typeService.deleteType(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/types";
    }
}





















