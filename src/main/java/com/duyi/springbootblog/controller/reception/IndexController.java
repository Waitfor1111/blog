package com.duyi.springbootblog.controller.reception;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.BlogService;
import com.duyi.springbootblog.service.ColumnistService;
import com.duyi.springbootblog.service.TagService;
import com.duyi.springbootblog.util.MarkdownUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class IndexController {


    @Autowired
    ColumnistService columnistService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;


    @GetMapping("index")
    public String test(Model model) {
        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        List<Columnist> columnists = columnistService.selectAll();
        model.addAttribute("types", columnists);
        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);
        //分页 默认每页显示8调数据
        PageHelper.startPage(1, 8);
        PageInfo<Blog> pageInfo = blogService.getBlogPaging();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogNav", 1);
        return "index";
    }


    // 博客的条件查询
    @GetMapping("/getPaging")
    public String getPaging(@RequestParam Map<String, Object> map, Model model) {
        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);
        PageInfo<Blog> pageInfo = blogService.getBlogCondition(map);
        model.addAttribute("pageInfo", pageInfo);
        // 返回指定模板片段
        return "index::table_refresh";
    }


    @GetMapping("/blogPage/{id}")
    public String blogPage(@PathVariable Integer id, Model model) {

        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        Blog blog = blogService.getBlog(id);
        // 格式转换
        blog.setContent(MarkdownUtil.markdownToHtmlExtens(blog.getContent()));
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/columnistPage")
    public String columnistIdPage(Model model) {
        PageHelper.startPage(1, 8);
        // 专栏
        PageInfo<Columnist> pageInfo = columnistService.findColumnistByCondition(null);
        Columnist columnist = pageInfo.getList().get(0);
        int cid = columnist.getId();
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



    @GetMapping("/tagPage")
    public String tagPage(Model model) {

        PageHelper.startPage(1, 8);
        // 专栏
        PageInfo<Tag> pageInfo = tagService.findTagCondition(null);
        Tag tag = pageInfo.getList().get(0);
        int tid = tag.getId();
        HashMap<String, Object> map = new HashMap<>();
        map.put("tags", tid);
        PageInfo<Blog> blogList = blogService.getBlogCondition(map);
        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        // 对应第一个专栏ID的博客
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tagId", tid);
        model.addAttribute("blogList", blogList);
        model.addAttribute("blogNav", 3);
        return "tags";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        model.addAttribute("blogNav", 5);
        return "about";
    }

    @GetMapping("/archivesPage")
    public String archivesPage(Model model) {
        model.addAttribute("blogNav", 4);
        PageInfo<Blog> pageInfo = blogService.getBlogCondition(null);
        model.addAttribute("number", pageInfo.getTotal());
        HashMap<Integer, List<Blog>> years = new HashMap<>();
        List<Blog> blogList = pageInfo.getList();
        // 按照年份进行hashmap归档
        // key=年份 ， value=博客列表（List<Blog>）
        for (Blog blog : blogList) {
            Date blogDate = blog.getCreateTime();
            // yyyy
            Integer year = DateUtil.year(blogDate);
            // list --> yyyy
            if (years.get(year) == null) {
                // 对应年份没有博客
                List<Blog> list = new ArrayList();
                list.add(blog);
                years.put(year, list);
            } else {
                List<Blog> list = years.get(year);
                list.add(blog);
            }
        }
        // 2020 blogList
        // 2019 blogList
        model.addAttribute("years", years);
        List<Blog> newBlog = blogService.blogByFive();
        model.addAttribute("newBlog", newBlog);
        return "archives";
    }


    @GetMapping("/search")
    public String search(Model model, String title) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        PageInfo<Blog> pageInfo = blogService.getBlogCondition(map);
        model.addAttribute("blogs", pageInfo.getList());
        return "search";
    }



}
