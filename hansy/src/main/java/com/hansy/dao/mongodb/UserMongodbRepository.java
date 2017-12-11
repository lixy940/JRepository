package com.hansy.dao.mongodb;


import com.hansy.domain.primary.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/4/27 下午10:16.
 * @blog http://blog.didispace.com  mongodb数据库
 */
public interface UserMongodbRepository extends MongoRepository<User, Long> {

    User findByName(String name);

}
