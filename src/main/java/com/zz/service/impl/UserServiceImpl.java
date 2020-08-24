package com.zz.service.impl;

import com.zz.dao.UserDao;
import com.zz.pojo.User;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(User user) {

        return userDao.login(user);
    }

    @Override
    public List<User> findAll(Integer uid) {
        return userDao.findALl(uid);
    }

    @Override
    public User findBalance(Integer uid) {
        return userDao.findBalance(uid);
    }

    @Override
    public void updateHeadImg(Integer uid, String imgPath) {
        User user = new User();
        user.setUid(uid);
        user.setImgPath(imgPath);
        userDao.update(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

}
