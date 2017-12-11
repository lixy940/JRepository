package com.hansy.dao.mybaties.primary;


import com.hansy.domain.primary.User;

import java.util.List;

public interface UserMapper {
	List<User> getAll();

	User getOne(Long id);

	void insert(User user);

//	void update(User user);

}
