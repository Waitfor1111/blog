package com.duyi.springbootblog.service.impl;

import com.duyi.springbootblog.dao.CommentDao;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Comment;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    CommentDao dao;

    @Override
    public List<Comment> findCommentAll() {
        return dao.findCommentAll();
    }

    @Override
    public PageInfo<Comment> findByConditionByColumnist(Map<String, Object> map) {
        List<Comment> columnistList = dao.findCommentByCondition(map);
        PageInfo<Comment> pageInfo = new PageInfo<>(columnistList);
        return pageInfo;
    }

    @Override
    public int delById(int id) {
        Comment comment = new Comment(id, null, null, null, null, null, null, null, null,null);
        return dao.delById(comment);
    }

    @Override
    public PageInfo<Comment> findTagCondition(Map map) {
        List comment = dao.findCommentByCondition(map);
        PageInfo<Comment> pageInfo = new PageInfo<>(comment);
        return pageInfo;
    }
}
