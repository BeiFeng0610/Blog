package com.beifeng.web.controller;

import com.beifeng.domain.*;
import com.beifeng.service.FriendLinkService;
import com.beifeng.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/28 23:57
 */
@Controller
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    @Autowired
    MessageService messageService;

    @Value("${Comment.avatar}")
    private String avatar;

    @GetMapping("friendLink")
    public String friendLink(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                             Model model){
        System.out.println("进入友链页面操作");
        PageHelper.startPage(pageNum, 10);

        List<Message> messages = messageService.getMessages();

        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        model.addAttribute("pageInfo", pageInfo);

        List<FriendLink> friends = friendLinkService.getFriendsByASC();
        model.addAttribute("friends", friends);

        return "friendLink";
    }

    @PostMapping("friendLink/pageNum")
    public String pageNum(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                             Model model){
        System.out.println("执行友链页面分页操作");
        PageHelper.startPage(pageNum, 10);

        List<Message> messages = messageService.getMessages();

        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        model.addAttribute("pageInfo", pageInfo);

        return "friendLink :: messageList";
    }

    @PostMapping("/friendLink/message")
    public String post(Message message, HttpSession session){
        System.out.println("进入添加留言操作");

        message.setAdminComment(false);
        User user = (User) session.getAttribute("user");

        if (user!=null){
            message.setNickname(user.getNickname());
            message.setEmail(user.getEmail());
            message.setAvatar(user.getAvatar());
            message.setAdminComment(true);
        }else {
            message.setAvatar(avatar);
        }

        messageService.saveMessage(message);

        return "redirect:/friendLink/messages";
    }

    @GetMapping("/friendLink/messages")
    public String friendLinkMessage(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                                    Model model){
        // 评论成功回到第一页
        pageNum = 1;
        PageHelper.startPage(pageNum, 10);
        List<Message> messages = messageService.getMessages();
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        model.addAttribute("pageInfo", pageInfo);

        return "friendLink :: messageList";
    }

    @PostMapping("/friendLink/message/delete")
    public String deleteComment(@RequestParam("removeCommentId") String messageId,
                                RedirectAttributes redirectAttributes){
        System.out.println("执行删除留言");

        String msg = messageService.deleteMessageById(messageId);

        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/friendLink";
    }

}
