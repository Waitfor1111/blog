package com.duyi.springbootblog.controller.admin;


import cn.hutool.core.convert.Convert;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Comment;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.CommentService;
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
public class CommentController {

    @Autowired
    CommentService commentService;


    //跳转评论页并查出所有数据
    @GetMapping("/commentPage")
    public String tagsPage(Model model) {

        PageHelper.startPage(1, 8);
        List<Comment> commentAll = commentService.findCommentAll();
        PageInfo<Comment> pageInfo = new PageInfo<>(commentAll);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navIndex", 4);
        return "admin/comments";
    }

   //评论搜索
    @GetMapping("/findConditionByComment")
    public String findByConditionByColumnist(@RequestParam Map<String, Object> map, Model model) {


        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);
        PageInfo<Comment> pageInfo = commentService.findByConditionByColumnist(map);
        model.addAttribute("pageInfo", pageInfo);
        // 返回指定模板片段
        return "admin/comments::table_refresh";
    }

    //删除评论
    @DeleteMapping("/comment")
    public String delTag(@RequestParam Map<String, Object> map, Model model) {
        int id = Convert.toInt(map.get("id"));

        commentService.delById(id);
        int pageNum = Convert.toInt(map.get("pageNum"));
        System.out.println("pageNum"+pageNum);
        PageHelper.startPage(pageNum, 8);

        PageInfo<Comment> pageInfo = commentService.findTagCondition(map);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/comments::table_refresh";
    }
//
//
//    //增加标签跳转
//    @GetMapping("/tagAddPage")
//    public String tagAddPage() {
//        return "admin/tag_add";
//    }
//
//    //添加标签
//    @PostMapping("/tagAdd")
//    public String tagAdd(String name) {
//        Tag tag = tagService.findByName(name);
//        if (tag != null) {
//            //代表标签已存在  让他的个数加一
//            tagService.tagUpdate(name);
//        } else {
//            //如果不为空  则表示没有这个标签
//            tagService.insertTag(new Tag(null, name, 1, new Date()));
//        }
//        return "redirect:/admin/tagPage";
//    }
//
//
//    //分类标签编辑
//    @GetMapping("/editTag/{id}")
//    public String editTag(@PathVariable int id, Model model) {
//        Tag tag = tagService.getTag(id);
//        if (tag == null) {
//        }
//        model.addAttribute("tag", tag);
//        return "/admin/tag_edit";
//    }
//
//    //分类标签修改
//    @PostMapping("/tagUpdate")
//    public String columnUpdate(Tag tag) {
//        tag.setCreateTime(new Date());
//        tagService.updateTag(tag);
//        return "redirect:/admin/tagPage";
//    }


}
