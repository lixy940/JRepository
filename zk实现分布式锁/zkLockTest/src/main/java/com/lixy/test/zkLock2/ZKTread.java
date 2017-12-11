package com.lixy.test.zkLock2;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

public class ZKTread extends Thread {

	private ZkClient zkClient;
	private String basePath;
	private String lockName;
	public ZKTread() {

	}

	public ZKTread(ZkClient zkClient,String basePath,String lockName) {
		super();
		this.zkClient = zkClient;
		this.basePath = basePath;
		this.lockName = lockName;
	}

	public void run() {

		SimpleDistributedLockMutex simpleDistributedLockMutex = new SimpleDistributedLockMutex(this.zkClient, this.basePath,this.lockName);
		try {

			boolean acquire = simpleDistributedLockMutex.acquire(20, TimeUnit.SECONDS);
			if (acquire) {
				System.out.println("获取锁成功");
				int n=10000;
				Thread.sleep(n);
				System.out.println(n+"ms后业务处理完成");
			} else {
				System.out.println("获取锁失败");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				simpleDistributedLockMutex.release();
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName()+"解锁失败:" + e.getMessage());
			}
		}

	}
}
