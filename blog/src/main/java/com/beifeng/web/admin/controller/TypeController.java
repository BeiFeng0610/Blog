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
    private TypeService typeService;

    /*分页展示类型*/
    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                        Model model){

        System.out.println("进入分类展示操作");
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/types";

    }

    @PostMapping("/types/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Model model){

        System.out.println("执行动态分页查询操作");
        // 按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10,orderBy);
        List<Type> list = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);

        model.addAttribute("pageInfo", pageInfo);

        return "admin/types :: typeList";

    }

    /*添加类型*/
    @GetMapping("/types/input")
    public String saveInput(Model model){
        System.out.println("进入添加分类操作");

        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /*添加类型校验*/
    @PostMapping("/types")
    public String saveType(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        System.out.println("执行添加分类信息校验");

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
    @GetMapping("/types/input/{id}")
    public String updateInput(@PathVariable String id, Model model){
        System.out.println("进入修改分类操作");

        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /*编辑修改分类*/
    @PostMapping("/types/{id}")
    public String updateType(@Valid Type type, BindingResult result,String id, RedirectAttributes attributes){
        System.out.println("执行修改分类信息校验操作");

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
    @GetMapping("/types/delete/{id}")
    public String deleteType(@PathVariable String id,RedirectAttributes attributes){
        System.out.println("执行删除分类操作");

        String msg = typeService.deleteType(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/types";
    }
}





















