package com.zz.dao;

import com.zz.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    User login(User user);

    List<User> findALl(Integer uid);

    User findBalance(Integer uid);

    void update(User user);

    User findByCode(String code);


}
