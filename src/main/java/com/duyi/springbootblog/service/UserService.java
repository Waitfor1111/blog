package com.duyi.springbootblog.service;

import com.duyi.springbootblog.domain.User;

public interface UserService {

    User login(String name, String password);


}
