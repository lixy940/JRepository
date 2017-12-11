package com.hansy.mybaties;

import com.hansy.Application;
import com.hansy.dao.mybaties.secondary.MessageMapper;
import com.hansy.domain.secondary.Message;
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
public class MessageMapperTest {

	@Autowired
	private MessageMapper messageMapper;

	@Test
	public void testInsert() throws Exception {
		messageMapper.insert(new Message("aa", "a123456"));
		messageMapper.insert(new Message("bb", "b123456"));
		messageMapper.insert(new Message("cc", "b123456"));

		Assert.assertEquals(3, messageMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<Message> messages = messageMapper.getAll();
		if(messages==null || messages.size()==0){
			System.out.println("is null");
		}else{
			System.out.println(messages.toString());
		}
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		Message message = messageMapper.getOne(6l);
		System.out.println(message.toString());
/*		message.setName("neo");
		messageMapper.update(message);
		Assert.assertTrue(("neo".equals(messageMapper.getOne(6l).getName())));*/
	}

}