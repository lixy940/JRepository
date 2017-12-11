package com.yue.mapper;


import com.yue.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface UserMapper {

    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

}
