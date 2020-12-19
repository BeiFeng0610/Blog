package com.beifeng;


import com.beifeng.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class BlogApplicationTests {

    @Test
    public static void main(String[] args) {
        String email = "www.1611606790@qq.COM";
        if (email.trim().toLowerCase().contains("@qq.com")){
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(email);
            System.out.println(m.replaceAll("").trim());
        }

    }

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * @Description:发送简单邮件(收件人，主题，内容都暂时写死)
     * 访问地址：http://localhost:8080/mail/sendSimpleMail
     * @return
     * @author:zoey
     * @date:2018年3月16日
     */
    /*@Test
    void sendSimpleMail(){
        String to = "1611606790@qq.com";
        String subject = "test simple mail";
        String content = "hello, this is simple mail";
        mailService.sendSimpleMail(to, subject, content);
    }*/

    @Test
    void sendHtmlMail() {
        String to = "1611606790@qq.com";
        String subject = "test html mail";
        String content = "hello, this is html mail";
        mailService.sendHtmlMail(to, subject, content);
    }

    @Test
    void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("user", "zoey");
        context.setVariable("web", "夏梦雪");
        context.setVariable("company", "测试公司");
        context.setVariable("product","梦想产品");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("1611606790@qq.com","主题：这是模板邮件",emailContent);
    }

}
