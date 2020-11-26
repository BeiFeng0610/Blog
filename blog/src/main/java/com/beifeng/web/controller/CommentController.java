package com.beifeng.web.controller;

import com.beifeng.domain.Comment;
import com.beifeng.domain.User;
import com.beifeng.service.CommentService;
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

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/17 22:32
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

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
            comment.setAvatar(avatar);
        }

        commentService.saveComment(comment);

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
