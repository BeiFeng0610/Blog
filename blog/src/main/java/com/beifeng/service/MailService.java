package com.beifeng.service;

import com.beifeng.domain.Comment;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/12/19 19:29
 */
public interface MailService {

    //发送普通邮件
    /*void sendSimpleMail(String to,String subject,String content);*/
    //发送Html邮件
    void sendHtmlMail(String to,String subject,String content);

    void sendTemplateMail(Comment comment);

}
