package com.beifeng.web.admin.controller;

import com.beifeng.domain.User;
import com.beifeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/1 19:27
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @author: BeiFeng
     * @description: TODO
     * @date: 2020/11/1 19:58
     * @param
     * @return java.lang.String
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }


    /**
     * @author: BeiFeng
     * @description: 登录校验
     * @date: 2020/11/1 20:00
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return java.lang.String
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
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
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
