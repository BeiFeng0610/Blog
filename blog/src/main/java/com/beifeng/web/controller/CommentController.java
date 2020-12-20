package com.beifeng.web.controller;

import com.beifeng.domain.Comment;
import com.beifeng.domain.User;
import com.beifeng.service.CommentService;
import com.beifeng.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/17 22:32
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MailService mailService;

    @Value("${Comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String Comments(@PathVariable String blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);

        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        System.out.println("进入添加评论操作");

        comment.setAdminComment(false);
        User user = (User) session.getAttribute("user");

        if (user!=null){
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            // 判断邮箱是否为qq邮箱
            if (comment.getEmail().trim().toLowerCase().contains("@qq.com")){
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(comment.getEmail());
                comment.setAvatar("http://q1.qlogo.cn/g?b=qq&nk="+m.replaceAll("").trim()+"&s=100");
            }else {
                // 如果不是正确的qq邮箱，使用默认头像
                comment.setAvatar(avatar);
            }
        }

        commentService.saveComment(comment);
        mailService.sendTemplateMail(comment);

        return "redirect:/comments/" + comment.getBlogId();
    }

    @PostMapping("/comment/edit")
    public String updateComment(@RequestParam("editCommentId") String commentId,
                                @RequestParam("editComment") String editComment,
                                @RequestParam("editBlogId") String blogId,
                                RedirectAttributes redirectAttributes){
        /*System.out.println(editCommentId+"----------"+editComment+blogId);*/
        String msg = commentService.editCommentById(commentId, editComment);

        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/blog/" + blogId;
    }

    @PostMapping("/comment/delete")
    public String deleteComment(@RequestParam("removeCommentId") String commentId,
                                @RequestParam("editBlogId") String blogId,
                                RedirectAttributes redirectAttributes){
        /*System.out.println(editCommentId+"----------"+editComment+blogId);*/
        String msg = commentService.deleteCommentById(commentId);

        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/blog/" + blogId;
    }

}
