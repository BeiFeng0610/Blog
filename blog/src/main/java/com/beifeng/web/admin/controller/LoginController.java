package com.beifeng.web.admin.controller;

import com.beifeng.domain.User;
import com.beifeng.execption.NotAdminException;
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
        System.out.println("进入到登录操作");
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
        System.out.println("进行登录信息验证");
        User user = userService.checkUser(username.trim(), password.trim());
        if (user != null){
            if (user.getType()!=1){
                NotAdminException notAdminException = new NotAdminException("请使用管理员账号登录");
                attributes.addFlashAttribute("message", notAdminException.getMessage());
                return "redirect:/admin";
            }else {
                user.setPassword(null);
                session.setAttribute("user",user);
                return "admin/index";
            }
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
        System.out.println("执行用户登出操作");
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String index(){
        System.out.println("返回首页");
        return "admin/index";
    }

    @GetMapping("/user/update")
    public String UserInput(){
        System.out.println("进入账户编辑操作");

        return "admin/user-update";
    }

    @PostMapping("/user/update")
    public String updateUser(User user,
                             RedirectAttributes attributes,
                             HttpSession session){
        System.out.println("更新账户信息");

        user.setPassword(user.getPassword().trim());
        user.setUsername(user.getUsername().trim());

        String msg = userService.updateUser(user);

        if (msg!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
        }

        attributes.addFlashAttribute("msg", msg);

        return "redirect:/admin/index";
    }

}
