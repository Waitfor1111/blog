package com.duyi.springbootblog.controller.admin;


import cn.hutool.core.convert.Convert;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.service.ColumnistService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ColumnistController {

    @Autowired
    ColumnistService columnistService;


    //跳转分类页并查出所有数据
    @GetMapping("/blogColumnistPage")
    public String blogColumnistPage(Model model) {

        PageHelper.startPage(1, 8);
        PageInfo<Columnist> pageInfo = columnistService.getColumnPaging();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("navIndex",2);
        return "admin/columnist";
    }

    // 分类的条件查询
    @GetMapping("/findConditionByColumnist")
    public String findByConditionByColumnist(@RequestParam Map<String, Object> map, Model model) {

        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);

        PageInfo<Columnist> pageInfo = columnistService.findColumnistByCondition(map);

        model.addAttribute("pageInfo", pageInfo);

        // 返回指定模板片段
        return "admin/columnist::table_refresh";
    }

    //分类删除
    @DeleteMapping("/delColumnist")
    public String delColumnist(@RequestParam Map<String, Object> map, Model model) {
        int id = Convert.toInt(map.get("id"));

        columnistService.columnDel(id);
        int pageNum = Convert.toInt(map.get("pageNum"));
        PageHelper.startPage(pageNum, 8);

        PageInfo<Columnist> pageInfo = columnistService.findColumnistByCondition(map);
        model.addAttribute("pageInfo", pageInfo);

        return "admin/columnist::table_refresh";
    }

    //分类标签新增跳转
    @GetMapping("/columnistAddPage")
    public String columnistAddPage() {

        return "admin/columnist_add";
    }

    //分类标签新增
    @PostMapping("/columnistAdd")
    public String columnistAdd(String name, String intro) {

        Columnist columnist = new Columnist();
        columnist.setName(name);
        columnist.setIntro(intro);
        columnist.setCreateTime(new Date());
        columnist.setColumnistState(0);
        int code = columnistService.addColun(columnist);
        if (code < 1) {
            //添加失败
            return null;
        } else {
            //后期修改  跳转到列表页
            return "redirect:/admin/blogColumnistPage";
        }
    }


    //分类标签编辑
    @GetMapping("/editColumnist/{id}")
    public String editBlog(@PathVariable int id, Model model) {
        Columnist columnist = columnistService.getColumn(id);
        if (columnist == null) {
        }
        model.addAttribute("col", columnist);
        return "/admin/columnist_edit";
    }


    //分类标签修改
    @PostMapping("/columnistUpdate")
    public String columnUpdate(Columnist columnist) {
        columnist.setUpdateTime(new Date());
         columnistService.updateColumn(columnist);
        return "redirect:/admin/blogColumnistPage";
    }


}
