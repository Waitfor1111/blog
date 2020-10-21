package com.duyi.springbootblog.dao;

import com.duyi.springbootblog.domain.Columnist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ColumnistDao {

    List<Columnist> findColumnistAll();

    List<Columnist> findColumnistByCondition(Map<String, Object> map);

    List<Columnist> findColumnistByIds(List list);

    int updateColumnist(Columnist columnist);

    int insertColumnist(Columnist columnist);

}
