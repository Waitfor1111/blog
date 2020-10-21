package com.duyi.springbootblog.service;

import com.duyi.springbootblog.domain.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BlogService {

    int addBlog(Blog blog);

    List<Blog> findAll();

    PageInfo<Blog> getBlogPaging();

    PageInfo<Blog> getBlogCondition(Map<String, Object> map);

    int blogDel(int id);

    Blog getBlog(int id);

    int updateBlog(Blog blog);

    List<Blog> blogByFive();


}
