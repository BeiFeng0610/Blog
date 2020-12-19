package com.beifeng.web.controller;

import com.beifeng.service.ArchivesService;
import com.beifeng.service.BlogService;
import com.beifeng.vo.ArchivesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author BeiFeng
 * @version 1.0
 * @date 2020/11/23 23:33
 */
@Controller
public class ArchivesController {

    @Autowired
    private ArchivesService archivesService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){

        Integer blogCount = blogService.getBlogCount();
        Map<String, List<ArchivesVo>> archivesMap = archivesService.getArchivesByYear();

        model.addAttribute("blogCount", blogCount);
        model.addAttribute("archivesMap", archivesMap);

        return "archives";
    }
}
