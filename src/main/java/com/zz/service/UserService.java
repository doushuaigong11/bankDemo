package com.zz.service;

import com.zz.pojo.User;

import java.util.List;

public interface UserService {
    User login(User user);

    List<User> findAll(Integer uid);

    User findBalance(Integer uid);

    void updateHeadImg(Integer uid, String imgPath);

    void update(User user);
}
