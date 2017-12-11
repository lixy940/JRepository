package com.hansy.service;

import com.hansy.domain.primary.User;
import com.hansy.domain.secondary.Message;

import java.util.List;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/17 下午7:04.
 * @blog http://blog.didispace.com
 */
public interface UserService {

    //=====jdbc操作数据源test1============================

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create_p(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName_p(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers_p();

    /**
     * 删除所有用户
     */
    void deleteAllUsers_p();

//======操作数据源test2========================================
    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create_s(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName_s(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers_s();

    /**
     * 删除所有用户
     */
    void deleteAllUsers_s();

    //========jpa数据源操作===================

    void saveUser(User user);

    void saveMessage(Message message);

    List<User> findAllUsers();

    List<Message> findAllMessages();

    void deleteAllMessagesJPA();

    void deleteAllUsersJPA();
}
