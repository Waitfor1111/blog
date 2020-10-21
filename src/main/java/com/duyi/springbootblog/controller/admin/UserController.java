package com.duyi.springbootblog.controller.admin;


import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.springbootblog.domain.User;
import com.duyi.springbootblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;



    // 登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {

        // 删除session中的user对象
        session.removeAttribute("user");

        return "redirect:/login";
    }

    @PostMapping("/user/login")
    public String login(String name, String password, HttpSession session, Model model) {

//        System.out.println("name=" + name);
        System.out.println("password=" + DigestUtil.md5Hex(password));
        User user = userService.login(name, password);
        if (user == null) {
            model.addAttribute("tip", "账号或密码不正确");
            return "admin/login";
        }
        session.setAttribute("user", user);
        // 重定向 浏览器发送的请求
        return "redirect:/admin/index";
    }




}
