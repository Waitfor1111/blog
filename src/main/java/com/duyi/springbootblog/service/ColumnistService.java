package com.duyi.springbootblog.service;

import com.duyi.springbootblog.domain.Blog;
import com.duyi.springbootblog.domain.Columnist;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ColumnistService {

    List<Columnist> selectAll();

    PageInfo<Columnist> findColumnistByCondition(Map<String, Object> map);

    PageInfo<Columnist> getColumnPaging();

    int columnDel(int id);

    Columnist getColumn(int id);

    int addColun(Columnist columnist);

    int updateColumn(Columnist columnist);

}
