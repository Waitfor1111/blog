package com.duyi.springbootblog.service.impl;

import com.duyi.springbootblog.dao.BlogDao;
import com.duyi.springbootblog.dao.ColumnistDao;
import com.duyi.springbootblog.dao.TagDao;
import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    ColumnistDao columnistDao;

    @Autowired
    TagDao tagDao;

    private Boolean updateCount(Blog blog, int count) {
        //添加博客 对应专栏的数量也增加
        List list = new ArrayList();
        list.add(blog.getColumnId());

        List<Columnist> columnistList = columnistDao.findColumnistByIds(list);
        if (columnistList == null || columnistList.size() == 0) {
            return false;
        }
        Columnist columnist = columnistList.get(0);
        Integer columCount = columnist.getBlogCount();
        columnist.setBlogCount(columCount + count);
        columnistDao.updateColumnist(columnist);

        //添加博客  对应的标签也增加
        String tags = blog.getTags();
        List<String> split = Arrays.asList(tags.split(","));
        List<Tag> tagByIds = tagDao.findTagByIds(split);
        if (tagByIds == null || tagByIds.size() == 0) {
            return false;
        }
        for (Tag tag : tagByIds) {
            Integer blogCount = tag.getBlogCount();
            tag.setBlogCount(blogCount + count);
            tagDao.updateTag(tag);
        }
        return true;
    }

    @Override
    @Transactional
    public int addBlog(Blog blog) {


        if (blog.getBlogState() == 1) {

            blog.setPublishDate(new Date());
            if (!updateCount(blog, 1)) {
                return -2;
            }

        }
        //博客创建时间
        blog.setCreateTime(new Date());
        //博客修改时间
        blog.setUpdateTime(new Date());
        return blogDao.insertBlog(blog);
    }

    @Override
    public List<Blog> findAll() {
        return blogDao.findBlogAll();
    }

    //调用之前必须设置参数 当前页和每页数据
    // PageHelper.startPage()
    //分页
    @Override
    public PageInfo<Blog> getBlogPaging() {
        List<Blog> list = blogDao.findBlogAll();
        this.getBlogColumnist(list);

        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //搜索
    @Override
    public PageInfo<Blog> getBlogCondition(Map<String, Object> map) {
        List<Blog> blogList = blogDao.findBlogByCondition(map);
        this.getBlogColumnist(blogList);

        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        return pageInfo;
    }

    //删除博客
    @Override
    public int blogDel(int id) {
        List<Object> list = new ArrayList<>();
        list.add(id);
        //先查出来博客
        List<Blog> blogList = blogDao.findBlogByIds(list);
        //如果存在就改变状态
        if (list == null || list.size() == 0) {
            return 0;
        }
        Blog blog = blogList.get(0);
        blog.setBlogState(-1);
        return blogDao.updateBlog(blog);
    }

    @Override
    public Blog getBlog(int id) {
        List<Object> list = new ArrayList<>();
        list.add(id);

        List<Blog> blogList = blogDao.findBlogByIds(list);
        if (blogList == null || blogList.size() == 0) {
            return null;
        }
        this.getBlogColumnist(blogList);
        return blogList.get(0);
    }

    @Override
    public int updateBlog(Blog blog) {

        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }




    private void getBlogColumnist(List<Blog> bList) {
        if (bList == null || bList.size() == 0) {
            return;
        }
        for (Blog blog : bList) {
            ArrayList ids = new ArrayList();
            ids.add(blog.getColumnId());
            List<Columnist> list = columnistDao.findColumnistByIds(ids);
            if (list == null || list.size() == 0) {
                // 没有对应的专栏
                return;
            }

            Columnist col = list.get(0);
            blog.setColumnist(col);
        }
    }


    @Override
    public List<Blog> blogByFive() {

       return  blogDao.blogByFive();
    }


}
