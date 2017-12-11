package com.hansy;

import com.hansy.dao.mongodb.UserMongodbRepository;
import com.hansy.domain.primary.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
@SpringBootTest
public class MongodbTest {

    @Autowired
    private UserMongodbRepository mongodbRepository;
    
    @Before
    public void setUp() {
        mongodbRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        // 创建三个User，并验证User总数
        mongodbRepository.save(new User(1L, "didi", 30));
        mongodbRepository.save(new User(2L, "mama", 40));
        mongodbRepository.save(new User(3L, "kaka", 50));
        Assert.assertEquals(3, mongodbRepository.findAll().size());

        // 删除一个User，再验证User总数
        User u = mongodbRepository.findOne(1L);
        mongodbRepository.delete(u);
        Assert.assertEquals(2, mongodbRepository.findAll().size());

        // 删除一个User，再验证User总数
        u = mongodbRepository.findByName("mama");
        mongodbRepository.delete(u);
        Assert.assertEquals(1, mongodbRepository.findAll().size());

    }
}
