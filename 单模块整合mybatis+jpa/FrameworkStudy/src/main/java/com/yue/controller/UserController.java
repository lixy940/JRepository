package com.yue.controller;
import com.yue.bean.User;
import com.yue.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/getById")
    @ResponseBody
    private User getById(long id) {
        logger.info("id:"+id);
        return userService.getUserById(id);
    }

    @RequestMapping("/save")
    @ResponseBody
    private User save() {
        User user = new User("xixi", 44);
         userService.insertUser(user);
        return user;
    }
}
