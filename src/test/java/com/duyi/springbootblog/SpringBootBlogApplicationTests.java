package com.duyi.springbootblog;

import com.duyi.springbootblog.dao.BlogDao;
import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Comment;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.BlogService;
import com.duyi.springbootblog.service.CommentService;
import com.duyi.springbootblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBootBlogApplicationTests {

    @Autowired
    TagService Tagservice;

    @Autowired
    CommentService commentService;

    @Autowired
    BlogDao dao;

    @Autowired
    BlogService service;

    void contextLoads() {

        Tag tag = new Tag(1, "Java", 1, new Date());
        Tagservice.insertTag(tag);
    }

    void t1() {
        List<Tag> tag = Tagservice.findAllTag();
        System.out.println(tag);
    }


    void t2() {
        List<Tag> tag = Tagservice.findByTag();
        System.out.println(tag);
    }

    void t3() {
        List<Comment>  comments = commentService.findCommentAll();

        System.out.println(comments);
    }

    void t4(){

        PageHelper.startPage(1,2);
        PageInfo<Blog> blogPaging = service.getBlogPaging();
    }

    public void testInsertBlog() {

        for (int i = 0; i <50; i++) {

            Blog blog = new Blog();
            blog.setId(100 + i);
            blog.setTitle("你好" + i);
            blog.setContent("内容" + i);
//            blog.setSummary("中文" + i);
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setColumnId(1);
            blog.setTags("1");
            blog.setComments("c");
            blog.setBlogImg("aaa");
            blog.setAdmireState(1);
            blog.setCommentState(1);
            blog.setRecommendState(1);
            blog.setReprintState(1);
            blog.setBlogState(0);
            blog.setViews(100);
            blog.setUpdateTime(new Date());

            dao.insertBlog(blog);
        }
    }

}
