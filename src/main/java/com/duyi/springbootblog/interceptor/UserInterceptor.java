package com.duyi.springbootblog.interceptor;

import cn.hutool.core.util.ObjectUtil;

import com.duyi.springbootblog.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
