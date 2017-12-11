package com.zenghong.test.zkLock1;

public class MyTread extends Thread {
	int num;

	public MyTread() {

	}

	public MyTread(int i) {
		super();
		num = i;
	}

	public void run() {
		System.out.println("hello " + num);

		DistLock distLock = new DistLock();
		String action="test1";
		//String num=String.valueOf(this.num);
		String num="1";
		try {
			boolean isLocked = distLock.lock(action, num, 10);
			if (isLocked) {
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
				distLock.unlock(action, num);
			} catch (Exception e) {
				System.out.println("解锁失败:" + e.getMessage());
			}
		}

		System.out.println("bye " + num);
	}
}
