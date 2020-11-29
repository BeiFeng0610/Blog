package com.beifeng.web.admin.controller;

import com.beifeng.domain.Message;
import com.beifeng.service.CommentService;
import com.beifeng.service.MessageService;
import com.beifeng.vo.AdminCommentsVo;
import com.beifeng.vo.AdminMessagesVo;
import com.beifeng.vo.BlogVo;
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
 * @date 2020/11/29 0:18
 */
@Controller
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    MessageService messageService;

    @GetMapping("/comments")
    public String comment(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                          Model model){
        System.out.println("进入评论管理页面操作");

        PageHelper.startPage(pageNum, 10);
        List<AdminCommentsVo> list = commentService.getComments();

        PageInfo<AdminCommentsVo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/comments";
    }

    @PostMapping("/comments/pageNum")
    public String pageNum(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                          Model model){
        System.out.println("执行评论管理页面分页操作");

        PageHelper.startPage(pageNum, 10);
        List<AdminCommentsVo> list = commentService.getComments();

        PageInfo<AdminCommentsVo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/comments :: commentList";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable String id,
                                RedirectAttributes attributes){
        System.out.println("执行删除评论");

        String msg = commentService.deleteCommentById(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/comments";
    }

/*-------------------------------------------------------------------------------------------------------*/

    @GetMapping("/messages")
    public String messages(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                          Model model){
        System.out.println("进入留言管理页面操作");

        PageHelper.startPage(pageNum, 10);
        List<AdminMessagesVo> list = messageService.getMessagesByDESC();

        PageInfo<AdminMessagesVo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/messages";
    }

    @PostMapping("/messages/pageNum")
    public String messagesPageNum(@RequestParam(required = false,defaultValue = "1",value = "pageNum") Integer pageNum,
                           Model model){
        System.out.println("进入留言管理页面操作");

        PageHelper.startPage(pageNum, 10);
        List<AdminMessagesVo> list = messageService.getMessagesByDESC();

        PageInfo<AdminMessagesVo> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/messages :: messageList";
    }

    @GetMapping("/messages/delete/{id}")
    public String deleteMessage(@PathVariable String id,
                                RedirectAttributes attributes){
        System.out.println("执行删除评论");

        String msg = messageService.deleteMessageById(id);
        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/messages";
    }
}
