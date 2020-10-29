package com.beifeng.web.controller;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/10/26 16:09
 */
import com.beifeng.execption.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    // @ResponseBody
    public String index(){
        // int i = 9/0;
        /*String blog = null;
        if (blog == null){
            throw new NotFoundException("博客不存在");
        }*/

        return "index";
    }

    @GetMapping("/blog")
    // @ResponseBody
    public String blog(){
        return "blog";
    }

    @GetMapping("/about")
    // @ResponseBody
    public String about(){
        return "about";
    }

    @GetMapping("/archives")
    // @ResponseBody
    public String archives(){
        return "archives";
    }

    @GetMapping("/tags")
    // @ResponseBody
    public String tags(){
        return "tags";
    }

    @GetMapping("/types")
    // @ResponseBody
    public String types(){
        return "types";
    }

    @GetMapping("/admin/blogs")
    // @ResponseBody
    public String adminBlogs(){
        return "/admin/blogs";
    }

    @GetMapping("/admin/blogsInput")
    // @ResponseBody
    public String adminBlogsInput(){
        return "/admin/blogs-input";
    }
}
