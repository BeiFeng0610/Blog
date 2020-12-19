package com.beifeng.web.admin.controller;

import com.beifeng.domain.FriendLink;
import com.beifeng.service.FriendLinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/29 21:07
 */
@Controller
@RequestMapping("/admin")
public class AdminFriendLinksController {

    @Autowired
    private FriendLinkService friendLinkService;

    @GetMapping("/friendLinks")
    public String friendLinks(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                              Model model){

        System.out.println("进入友链管理页面操作");

        PageHelper.startPage(pageNum, 10);
        List<FriendLink> list = friendLinkService.getFriends();

        PageInfo<FriendLink> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/friendLinks";
    }

    @PostMapping("/friendLinks/pageNum")
    public String pageNum(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                              Model model){

        System.out.println("进入友链管理分页页面操作");

        PageHelper.startPage(pageNum, 10);
        List<FriendLink> list = friendLinkService.getFriends();

        PageInfo<FriendLink> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/friendLinks :: friendLinkList";
    }

    @GetMapping("/friendLinks/input")
    public String friendLinksInput(){
        System.out.println("进入友链添加页面");
        return "admin/friendLinks-input";
    }

    @PostMapping("/friendLinks/input")
    public String saveFriend(FriendLink friendLink, RedirectAttributes attributes){

        System.out.println("执行友链添加操作");

        String msg = friendLinkService.saveFriend(friendLink);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/friendLinks";
    }

    @GetMapping("/friendLinks/update/{id}")
    public String friendLinksUpdate(@PathVariable String id,Model model){
        System.out.println("进入友链更新页面");

        FriendLink friend = friendLinkService.getFriendById(id);
        model.addAttribute("friend", friend);

        return "admin/friendLinks-update";
    }

    @PostMapping("/friendLinks/update")
    public String updateFriend(FriendLink friendLink,RedirectAttributes attributes){
        System.out.println("执行友链更新操作");

        String msg = friendLinkService.updateFriendById(friendLink);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/friendLinks";
    }

    @GetMapping("/friendLinks/delete/{id}")
    public String deleteFriend(@PathVariable String id,RedirectAttributes attributes){
        System.out.println("执行删除友链操作");

        String msg = friendLinkService.deleteFriendById(id);

        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/friendLinks";
    }
}













