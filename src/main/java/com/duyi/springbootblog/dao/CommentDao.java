package com.duyi.springbootblog.dao;

import com.duyi.springbootblog.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface CommentDao {


    int insertComment(Comment comment);

    List<Comment> findCommentAll();

    List<Comment> findCommentByCondition(Map<String, Object> map);

    int delById(Comment comment);
}
