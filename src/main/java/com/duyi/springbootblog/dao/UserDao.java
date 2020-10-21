package com.duyi.springbootblog.dao;


import com.duyi.springbootblog.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface UserDao {

    User findUserByCondition(Map<String, Object> map);

    List<User> findUserAll();


}
