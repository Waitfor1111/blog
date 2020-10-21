package com.duyi.springbootblog.controller.reception;


import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.service.BlogService;
import com.duyi.springbootblog.service.ColumnistService;
import com.duyi.springbootblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexColumnistController {

    @Autowired
    ColumnistService columnistService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @RequestMapping("/columnistIdPage")
    public String columnistIdPage(Model model, int cid) {
        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        PageHelper.startPage(1, 8);
        // 专栏
        PageInfo<Columnist> pageInfo = columnistService.findColumnistByCondition(null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("columnId", cid);
        PageInfo<Blog> blogList = blogService.getBlogCondition(map);
        // 对应第一个专栏ID的博客
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("typeId", cid);
        model.addAttribute("blogList", blogList);
        model.addAttribute("blogNav", 2);
        return "columnist";
    }



}
