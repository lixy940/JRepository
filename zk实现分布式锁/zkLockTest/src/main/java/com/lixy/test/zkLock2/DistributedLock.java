package com.lixy.test.zkLock2;

import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @Author:li_shuai
 * @date:Create on 2017/11/28 15:00
 */
public interface DistributedLock {
    /**获取锁，如果没有得到就等待*/
    public  void acquire()  throws Exception;

    /**
     * 获取锁，直到超时
     * @param time 超时时间
     * @param unit time参数的单位
     * @return是否获取到锁
     * @throws Exception
     */
    public  boolean acquire (long time, TimeUnit unit)  throws Exception;

    /**
     * 释放锁
     * @throws Exception
     */
    public  void release()  throws Exception;
}
