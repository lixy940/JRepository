package com.hansy.dao.jpa.secondary;

import com.hansy.domain.secondary.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.hansy.com
 * @desc secondary包中的为数据源secondaryDataSource 例如:Message在数据源为mysql/oracle数据库test2
 */
@Component
public interface MessageRepository extends JpaRepository<Message, Long> {


}
