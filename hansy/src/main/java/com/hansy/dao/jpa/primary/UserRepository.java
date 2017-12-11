package com.hansy.dao.jpa.primary;

import com.hansy.domain.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/23 下午2:34.
 * @blog http://blog.hansy.com
 * @desc   primary包中的为数据源primaryDataSource 例如:User在数据源为mysql/oracle数据库test1
 */
@Component
public interface UserRepository extends JpaRepository<User, Long> {


}
