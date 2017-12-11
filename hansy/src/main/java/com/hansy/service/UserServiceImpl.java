package com.hansy.service;

import com.hansy.dao.jpa.primary.UserRepository;
import com.hansy.dao.jpa.secondary.MessageRepository;
import com.hansy.domain.primary.User;
import com.hansy.domain.secondary.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/17 下午6:44.
 * @blog http://blog.didispace.com
 */
//@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void create_p(String name, Integer age) {
        jdbcTemplate1.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public void deleteByName_p(String name) {
        jdbcTemplate1.update("delete from USER where NAME = ?", name);
    }

    @Override
    public Integer getAllUsers_p() {
        return jdbcTemplate1.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public void deleteAllUsers_p() {
        jdbcTemplate1.update("delete from USER");
    }

    @Override
    public void create_s(String name, Integer age) {
        jdbcTemplate2.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public void deleteByName_s(String name) {
        jdbcTemplate2.update("delete from USER where NAME = ?", name);
    }

    @Override
    public Integer getAllUsers_s() {
        return jdbcTemplate2.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public void deleteAllUsers_s() {
        jdbcTemplate2.update("delete from USER");
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteAllMessagesJPA() {
        messageRepository.deleteAll();
    }

    @Override
    public void deleteAllUsersJPA() {
       userRepository.deleteAll();
    }
}
