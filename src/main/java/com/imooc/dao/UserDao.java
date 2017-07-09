package com.imooc.dao;

import com.imooc.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */
public interface UserDao {

    List<User> select(User user);

    List<User> selectAll();
}
