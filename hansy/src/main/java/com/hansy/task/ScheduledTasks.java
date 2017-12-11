package com.hansy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 在上面的入门例子中，使用了@Scheduled(fixedRate = 5000) 注解来定义每过5秒执行的任务，对于@Scheduled的使用可以总结如下几种方式：
 *@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
 *@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
 *@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
 * 同时需要在Application中开启定时任务 通过注解 @EnableScheduling 实现
 *  **/
 //也可通过@Scheduled(cron="*/5 * * * * *") ：通过cron表达式定义规则

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }

}
