package com.hansy.mybaties;

import com.hansy.Application;
import com.hansy.dao.mybaties.primary.UserMapper;
import com.hansy.domain.primary.User;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
@SpringBootTest
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void testInsert() throws Exception {
		userMapper.insert(new User("aa", 12));
		userMapper.insert(new User("bb", 23));
		userMapper.insert(new User("cc", 32));

		Assert.assertEquals(3, userMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = userMapper.getAll();
		if(users==null || users.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(users.size());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = userMapper.getOne(19l);
		System.out.println(user.toString());
	/*	user.setName("neo");
		userMapper.update(user);
		Assert.assertTrue(("neo".equals(userMapper.getOne(19l).getName())));*/
	}

}