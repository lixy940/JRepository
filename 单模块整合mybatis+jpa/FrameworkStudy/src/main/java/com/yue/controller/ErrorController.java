package com.yue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/index")
    public String index() {
        return "views/index";
    }
}
