package com.duyi.springbootblog.service;


import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.domain.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TagService {


    int insertTag(Tag tag);

    List<Tag> findAllTag();

    List<Tag> findByTag();

    PageInfo<Tag> findByTags(Map<String, Object> map);

    int tagsDel(int id);

    PageInfo<Tag> findTagCondition(Map map);

    Tag findByName(String name);

    int tagUpdate(String name);

    Tag getTag(int id);

    int updateTag(Tag tag);


}
