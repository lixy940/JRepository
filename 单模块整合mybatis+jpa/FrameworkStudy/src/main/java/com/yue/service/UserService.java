package com.yue.service;


import com.yue.bean.User;

import java.util.List;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/17 下午7:04.
 * @blog http://blog.didispace.com
 */
public interface UserService {

    void insertUser(User user);

    User getUserById(Long id);

    List<User> getAll();

}
