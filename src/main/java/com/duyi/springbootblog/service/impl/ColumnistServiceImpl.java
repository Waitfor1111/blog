package com.duyi.springbootblog.service.impl;

import com.duyi.springbootblog.dao.ColumnistDao;
import com.duyi.springbootblog.domain.Columnist;
import com.duyi.springbootblog.service.ColumnistService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ColumnistServiceImpl implements ColumnistService {


    @Autowired
    ColumnistDao dao;

    @Override
    public List<Columnist> selectAll() {

        return dao.findColumnistAll();

    }

    @Override
    public PageInfo<Columnist> findColumnistByCondition(Map<String,Object> map) {

        List<Columnist> list = dao.findColumnistByCondition(map);
        PageInfo<Columnist> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }



    @Override
    public PageInfo<Columnist> getColumnPaging() {
        List<Columnist> list = dao.findColumnistAll();
        PageInfo<Columnist> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public int columnDel(int id) {
        List<Object> list = new ArrayList<>();
        list.add(id);

        List<Columnist> colunmList = dao.findColumnistByIds(list);
        //如果存在就改变状态
        if (colunmList == null || colunmList.size() == 0) {
            return 0;
        }
        Columnist columnist = colunmList.get(0);
        columnist.setColumnistState(-1);
        columnist.setUpdateTime(new Date());

        return dao.updateColumnist(columnist);
    }

    @Override
    public Columnist getColumn(int id) {
        List<Object> list = new ArrayList<>();
        list.add(id);

        List<Columnist> columnistList = dao.findColumnistByIds(list);
        if (columnistList == null || columnistList.size() == 0) {
            return null;
        }
        return columnistList.get(0);
    }

    @Override
    public int addColun(Columnist columnist) {
        return dao.insertColumnist(columnist);
    }

    @Override
    public int updateColumn(Columnist columnist) {
        return dao.updateColumnist(columnist);
    }
}
