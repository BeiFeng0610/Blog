package com.beifeng.service.impl;

import com.beifeng.dao.BlogMapper;
import com.beifeng.dao.CommentMapper;
import com.beifeng.domain.Blog;
import com.beifeng.domain.Comment;
import com.beifeng.service.MailService;
import com.beifeng.vo.SendEmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/12/19 19:30
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Value("${spring.mail.username}")
    private String from;

    /*@Override
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            System.out.println("简单邮件发送成功！");
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！"+e);
        }

    }*/
    @Transactional
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);   //true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
            System.out.println("html邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("发送html邮件时发生异常！"+e);
        }
    }

    /*发送模板邮件*/
    @Transactional
    @Async
    @Override
    public void sendTemplateMail(Comment comment){
        SendEmailVo sendEmailVo = new SendEmailVo();
        Blog blog = blogMapper.getBlogById(comment.getBlogId());
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
            Comment replyComment = commentMapper.getReplyCommendByReplyCommendId(comment.getReplyCommentId());
            sendEmailVo.setReplyNickname(replyComment.getNickname());
            sendEmailVo.setReplyContent(replyComment.getContent());
            sendEmailVo.setReplyEmail(replyComment.getEmail());
            //创建邮件正文
            Context context = new Context();
            context.setVariable("sendEmailVo",sendEmailVo);
            String emailContent = templateEngine.process("emailTemplate", context);
            sendHtmlMail(replyComment.getEmail(),title,emailContent);
        }else {
            if (""!=comment.getParentCommentId()&&null!=comment.getParentCommentId()){
                Comment replyComment = commentMapper.getReplyCommendByReplyCommendId(comment.getParentCommentId());
                sendEmailVo.setReplyNickname(replyComment.getNickname());
                sendEmailVo.setReplyContent(replyComment.getContent());
                sendEmailVo.setReplyEmail(replyComment.getEmail());
                //创建邮件正文
                Context context = new Context();
                context.setVariable("sendEmailVo",sendEmailVo);
                String emailContent = templateEngine.process("emailTemplate", context);
                sendHtmlMail(replyComment.getEmail(),title,emailContent);
            }else {
                if (!comment.getAdminComment()){
                    sendEmailVo.setReplyFlag(false);
                    //创建邮件正文
                    Context context = new Context();
                    context.setVariable("sendEmailVo",sendEmailVo);
                    String emailContent = templateEngine.process("emailTemplate", context);
                    sendHtmlMail(from,title,emailContent);
                }
            }
        }
    }
}
