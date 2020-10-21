package com.duyi.springbootblog.dao;


import com.duyi.springbootblog.domain.Tag;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
@Repository
public interface TagDao {

    int insertTag(Tag tag);

    List<Tag> findTagByCondition(Map<String, Object> map);

    int updateTag(Tag tag);

    List<Tag> findTagAll();

    int delTags(Tag tag);

    Tag findByName(String name);

    int tagUpdate(String name);

    List<Tag> findTagByIds(List list);



}
