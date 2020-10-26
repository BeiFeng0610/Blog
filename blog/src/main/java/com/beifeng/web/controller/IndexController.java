package com.beifeng.web.controller;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/10/26 16:09
 */
import com.beifeng.execption.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    // @ResponseBody
    public String index(){
        // int i = 9/0;
        String blog = null;
        if (blog == null){
            throw new NotFoundException("博客不存在");
        }
        return "index";
    }
}
