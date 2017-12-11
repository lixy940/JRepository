package com.hansy;

import com.hansy.domain.primary.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
@SpringBootTest
public class RedisTests {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("bbb", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("bbb"));
	}

	@Test
	public void testObj() throws Exception {
		User user=new User();
		ValueOperations<String, User> operations=redisTemplate.opsForValue();
		operations.set("com.neox", user);
		operations.set("com.neo.f", user,1, TimeUnit.SECONDS);//设置过期时间为1s
		Thread.sleep(1000);
		//redisTemplate.delete("com.neo.f");
		boolean exists=redisTemplate.hasKey("com.neo.f");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	}
}