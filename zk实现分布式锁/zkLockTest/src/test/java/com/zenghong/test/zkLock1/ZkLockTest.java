package com.zenghong.test.zkLock1;

import junit.framework.TestCase;

public class ZkLockTest extends TestCase {

	public static void main(String... args) {

		MyTread t1=new MyTread(1);

		MyTread t2=new MyTread(2);
		t1.start();
		t2.start();
	}
}
