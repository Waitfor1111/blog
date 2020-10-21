package com.duyi.springbootblog.controller.admin;


import cn.hutool.core.convert.Convert;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;

    //跳转标签页并查出所有数据
    @GetMapping("/tagPage")
    public String tagsPage(Model model) {

        PageHelper.startPage(1, 8);
        List<Tag> tagList = tagService.findAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navIndex", 3);
        return "admin/tags";
    }

    //标签搜索
    @GetMapping("/findConditionByTag")
    public String findByConditionByColumnist(@RequestParam Map<String, Object> map, Model model) {

        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);

        PageInfo<Tag> pageInfo = tagService.findByTags(map);
        model.addAttribute("pageInfo", pageInfo);
        // 返回指定模板片段
        return "admin/tags::table_refresh";
    }

    //删除标签
    @DeleteMapping("/delTag")
    public String delTag(@RequestParam Map<String, Object> map, Model model) {
        int id = Convert.toInt(map.get("id"));

        tagService.tagsDel(id);
        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);

        PageInfo<Tag> pageInfo = tagService.findTagCondition(map);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/tags::table_refresh";
    }


    //增加标签跳转
    @GetMapping("/tagAddPage")
    public String tagAddPage() {
        return "admin/tag_add";
    }

    //添加标签
    @PostMapping("/tagAdd")
    public String tagAdd(String name) {
        Tag tag = tagService.findByName(name);
        if (tag != null) {
            //代表标签已存在  让他的个数加一
            tagService.tagUpdate(name);
        } else {
            //如果不为空  则表示没有这个标签
            tagService.insertTag(new Tag(null, name, 1, new Date()));
        }
        return "redirect:/admin/tagPage";
    }


    //分类标签编辑
    @GetMapping("/editTag/{id}")
    public String editTag(@PathVariable int id, Model model) {
        Tag tag = tagService.getTag(id);
        if (tag == null) {
        }
        model.addAttribute("tag", tag);
        return "/admin/tag_edit";
    }

    //分类标签修改
    @PostMapping("/tagUpdate")
    public String columnUpdate(Tag tag) {
        tag.setCreateTime(new Date());
        tagService.updateTag(tag);
        return "redirect:/admin/tagPage";
    }


}
