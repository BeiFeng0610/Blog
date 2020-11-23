package com.beifeng.web.controller;

import com.beifeng.domain.User;
import com.beifeng.execption.NotAdminException;
import com.beifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/19 15:42
 */
@Controller
public class AdminLoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        System.out.println("进行登录信息验证");
        User user = userService.checkUser(username.trim(), password.trim());
        if (user != null){
            if (user.getType()!=1){
                NotAdminException notAdminException = new NotAdminException("请使用管理员账号登录");
                attributes.addFlashAttribute("message", notAdminException.getMessage());
                return "redirect:/login";
            }else {
                user.setPassword(null);
                session.setAttribute("user",user);
                return "redirect:/";
            }
        }else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/login";
        }

    }

    /**
     * @author: BeiFeng
     * @description: 用户注销
     * @date: 2020/11/1 20:01
     * @param session
     * @return java.lang.String
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("执行用户登出操作");
        session.removeAttribute("user");
        return "redirect:/";
    }
}
