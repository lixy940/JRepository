package com.hansy;

import com.hansy.domain.primary.User;
import com.hansy.domain.secondary.Message;
import com.hansy.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserServiceTests {

    @Autowired
    private UserService userSerivce;

    @Before
    public void setUp() {
        // 准备，清空user表
//        userSerivce.deleteAllUsers_p();
        userSerivce.deleteAllUsers_s();
        userSerivce.deleteAllMessagesJPA();
        userSerivce.deleteAllUsersJPA();
    }

    @Test
    public void testJdbc() throws Exception {
        // test1数据库插入5个用户
        userSerivce.create_p("a", 1);
        userSerivce.create_p("b", 2);
        userSerivce.create_p("c", 3);
        userSerivce.create_p("d", 4);
        userSerivce.create_p("e", 5);

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userSerivce.getAllUsers_p().intValue());

        // 删除两个用户
        userSerivce.deleteByName_p("a");
        userSerivce.deleteByName_p("e");

        // 查数据库，应该有5个用户
        Assert.assertEquals(3, userSerivce.getAllUsers_p().intValue());


        // test2数据库插入5个用户
        userSerivce.create_s("a", 1);
        userSerivce.create_s("b", 2);
        userSerivce.create_s("c", 3);
        userSerivce.create_s("d", 4);
        userSerivce.create_s("e", 5);

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, userSerivce.getAllUsers_s().intValue());

        // 删除两个用户
        userSerivce.deleteByName_s("a");
        userSerivce.deleteByName_s("b");

        // 查数据库，应该有5个用户
        Assert.assertEquals(3, userSerivce.getAllUsers_s().intValue());

    }


    @Test
    public void testJPA()throws Exception {
        User user1 = new User();
        user1.setName("xixi");
        user1.setAge(12);
        User user2 = new User();
        user2.setName("xixi");
        user2.setAge(12);
        userSerivce.saveUser(user1);
        userSerivce.saveUser(user2);
        Assert.assertEquals(2, userSerivce.findAllUsers().size());

        Message m1 = new Message();
        m1.setName("消息");
        m1.setContent("实际付款了当时的");
        userSerivce.saveMessage(m1);
        Message m2 = new Message();
        m2.setName("消息");
        m2.setContent("实际付款了当时的");
        userSerivce.saveMessage(m1);//持久化对象
        userSerivce.saveMessage(m2);
        Assert.assertEquals(2, userSerivce.findAllMessages().size());
    }
}
