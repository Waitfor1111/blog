package com.duyi.springbootblog.service;

import com.duyi.springbootblog.domain.Comment;
import com.duyi.springbootblog.domain.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<Comment> findCommentAll();

    PageInfo<Comment> findByConditionByColumnist(Map<String, Object> map);

    int delById(int id);

    PageInfo<Comment> findTagCondition(Map map);
}
