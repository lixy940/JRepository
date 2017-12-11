package com.yue.service.impl;

import com.yue.bean.User;
import com.yue.mapper.UserMapper;
import com.yue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/17 下午6:44.
 * @blog http://blog.didispace.com
 */
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public void insertUser(User user) {
        mapper.insert(user);
    }

    @Override
    public User getUserById(Long id) {
        return mapper.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return mapper.getAll();
    }

}
