package com.beifeng.web.controller;

import com.beifeng.domain.Blog;
import com.beifeng.domain.Comment;
import com.beifeng.domain.User;
import com.beifeng.service.BlogService;
import com.beifeng.service.CommentService;
import com.beifeng.service.MailService;
import com.beifeng.vo.SendEmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    private BlogService blogService;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${Comment.avatar}")
    private String avatar;

    @Value("${spring.mail.username}")
    private String myEmail;

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
        sendTemplateMail(comment);

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

    /*发送模板邮件*/
    private void sendTemplateMail(Comment comment){
        SendEmailVo sendEmailVo = new SendEmailVo();
        Blog blog = blogService.getBlogById(comment.getBlogId());
        String title = "北风小窝评论回复";

        sendEmailVo.setNickname(comment.getNickname());
        sendEmailVo.setContent(comment.getContent());
        sendEmailVo.setBlogName(blog.getTitle());
        sendEmailVo.setBlogId(comment.getBlogId());
        sendEmailVo.setReplyFlag(true);

        /*
            判断评论是否回复其他评论
                如果有，直接给被回复的评论发送邮件
                如果没有，判断是否为主评论
                    如果不是主评论，给主评论发送邮件
                    如果是主评论，判断是否为管理员评论
                        不是管理员评论，发送邮件给管理员
                        是管理员评论，不用发送邮件
        */
        if (""!=comment.getReplyCommentId()&&null!=comment.getReplyCommentId()){
            Comment replyComment = commentService.getCommentById(comment.getReplyCommentId());
            sendEmailVo.setReplyNickname(replyComment.getNickname());
            sendEmailVo.setReplyContent(replyComment.getContent());
            sendEmailVo.setReplyEmail(replyComment.getEmail());
            //创建邮件正文
            Context context = new Context();
            context.setVariable("sendEmailVo",sendEmailVo);
            String emailContent = templateEngine.process("emailTemplate", context);
            mailService.sendHtmlMail(replyComment.getEmail(),title,emailContent);
        }else {
            if (""!=comment.getParentCommentId()&&null!=comment.getParentCommentId()){
                Comment replyComment = commentService.getCommentById(comment.getParentCommentId());
                sendEmailVo.setReplyNickname(replyComment.getNickname());
                sendEmailVo.setReplyContent(replyComment.getContent());
                sendEmailVo.setReplyEmail(replyComment.getEmail());
                //创建邮件正文
                Context context = new Context();
                context.setVariable("sendEmailVo",sendEmailVo);
                String emailContent = templateEngine.process("emailTemplate", context);
                mailService.sendHtmlMail(replyComment.getEmail(),title,emailContent);
            }else {
                if (!comment.getAdminComment()){
                    sendEmailVo.setReplyFlag(false);
                    //创建邮件正文
                    Context context = new Context();
                    context.setVariable("sendEmailVo",sendEmailVo);
                    String emailContent = templateEngine.process("emailTemplate", context);
                    mailService.sendHtmlMail(myEmail,title,emailContent);
                }
            }
        }
    }
}
