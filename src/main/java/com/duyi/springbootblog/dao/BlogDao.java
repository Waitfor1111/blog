package com.duyi.springbootblog.dao;

import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogDao {

    int insertBlog(Blog blog);

    List<Blog> findBlogAll();

    List<Blog> findBlogByCondition(Map<String,Object> map);

    int updateBlog(Blog blog);

    List<Blog> findBlogByIds(List list);

    int updateColumnist(Columnist columnist);

    List<Blog> blogByFive();

}
