package com.duyi.springbootblog.controller.admin;


import cn.hutool.core.convert.Convert;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ColumnistService columnistService;

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    //跳转博客首页
    @GetMapping("/index")
    public String index(Model model) {
        List<Columnist> columnists = columnistService.selectAll();

        //分页 默认每页显示8调数据
        PageHelper.startPage(1, 8);
        PageInfo<Blog> pageInfo = blogService.getBlogPaging();
        List<Blog> blogs = pageInfo.getList();
        model.addAttribute("blogs", blogs);
        model.addAttribute("types", columnists);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navIndex", 1);
        return "admin/manage";
    }


    //添加用户页面跳转
    @RequestMapping("/blogAddPage")
    public String blogAdd(Model model) {
        List<Columnist> columnists = columnistService.selectAll();
        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);
        model.addAttribute("columnists", columnists);
        return "admin/blog_add";
    }

    //接受添加用户信息表单
    @PostMapping("/blogAdd")
    public String blogAdd(Blog blog) {
//        blog.setContent(MarkdownUtil.markdownToHtmlExtens(blog.getContent()));

        System.out.println(blog);

        int code = blogService.addBlog(blog);
        if (code < 1) {
            //添加失败
            return null;
        } else {
            //后期修改  跳转到列表页
            return "redirect:/admin/index";
        }
    }

    //搜索
    @RequestMapping("/findCondition")
    public String search(@RequestParam Map<String, Object> map, Model model) {

        Integer pageNum = Convert.toInt(map.get("pageNum"));

        //每页固定显示8条数据
        PageHelper.startPage(pageNum, 8);

        PageInfo<Blog> condition = blogService.getBlogCondition(map);
        model.addAttribute("pageInfo", condition);

        return "admin/manage::table_refresh";
    }

    //博客删除
    @DeleteMapping("/del")
    public String del(@RequestParam Map<String, Object> map, Model model) {
        int id = Convert.toInt(map.get("id"));

        int code = blogService.blogDel(id);
        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);

        PageInfo<Blog> pageInfo = blogService.getBlogCondition(map);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/manage::table_refresh";
    }


    //博客编辑
    @GetMapping("/editBlog/{id}")
    public String editBlog(@PathVariable int id, Model model) {
        Blog blog = blogService.getBlog(id);
        if (blog == null) {
        }
        List<Columnist> columnists = columnistService.selectAll();
        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);
        model.addAttribute("columnists", columnists);
        model.addAttribute("blog", blog);
        return "/admin/blog_edit";
    }

    //博客修改
    @PostMapping("/blogUpdate")
    public String blogUpdate(Blog blog) {
//        MarkdownUtil.markdownToHtmlExtens(blog.getContent());

        int code = blogService.updateBlog(blog);

        return "redirect:/admin/index";
    }


}
