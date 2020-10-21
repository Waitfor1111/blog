package com.duyi.springbootblog.service.impl;


import com.duyi.springbootblog.dao.TagDao;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Tag;
import com.duyi.springbootblog.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagDao dao;


    @Override
    public int insertTag(Tag tag) {

        System.out.println(tag);
        dao.insertTag(tag);
        return 0;
    }

    @Override
    public List<Tag> findAllTag() {
        return dao.findTagAll();
    }

    @Override
    public List<Tag> findByTag() {

        Map<String, Object> map = new HashMap<>();
        map.put("name", "spring");
        return dao.findTagByCondition(map);
    }

    @Override
    public PageInfo<Tag> findByTags(Map<String, Object> map) {
        List<Tag> tagList = dao.findTagByCondition(map);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        return pageInfo;
    }

    @Override
    public int tagsDel(int id) {
        Tag tag = new Tag(id, null, null, null);
        return dao.delTags(tag);
    }

    @Override
    public PageInfo<Tag> findTagCondition(Map map) {
        List list = dao.findTagByCondition(map);
        PageInfo<Tag> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Tag findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public int tagUpdate(String name) {
       return dao.tagUpdate(name);
    }

    @Override
    public Tag getTag(int id) {

        List<Object> list = new ArrayList<>();
        list.add(id);
        List<Tag> tagList =  dao.findTagByIds(list);
        if (tagList == null || tagList.size() == 0) {
            return null;
        }
        return tagList.get(0);
    }

    @Override
    public int updateTag(Tag tag) {
        return dao.updateTag(tag);
    }


}
