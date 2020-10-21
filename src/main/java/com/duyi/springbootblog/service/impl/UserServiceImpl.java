package com.duyi.springbootblog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

import com.duyi.springbootblog.dao.UserDao;
import com.duyi.springbootblog.domain.User;
import com.duyi.springbootblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;


    @Override
    public User login(String name, String password) {
        if (StrUtil.isEmpty(name) || StrUtil.isEmpty(password)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("password", DigestUtil.md5Hex(password));
        User user = dao.findUserByCondition(map);
        return user;
    }
}
