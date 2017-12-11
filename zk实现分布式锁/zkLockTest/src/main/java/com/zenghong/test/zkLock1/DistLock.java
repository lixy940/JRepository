package com.zenghong.test.zkLock1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import strman.Strman;



/**
 *
 * <p>
 * Description: 分布式锁
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @Author mac
 * @Datetime 2016年10月16日 下午3:23:46
 */
public class DistLock {
	private Logger LOG = LoggerFactory.getLogger(DistLock.class);

    private static final String rootPath = "distLock";

    private static final int DEFAULT_CONNECT_TIMEOUT = 2000;

    private static final int DEFAULT_SESSION_TIMEOUT = 2000;

    private static final int DEFAULT_LOCK_TIMEOUT = 10;

    private static final String BATCH = "_batch";

    private String connectionString;

    private int connectionTimeout;

    private int sessionTimeout;

    private CuratorFramework client;

    private Map<String, InterProcessMutex> locks;

    private Map<String, InterProcessMultiLock> batchlocks;

    public DistLock() {
    	try{
    		System.out.println("初始化开始");
            this.connectionString = "10.40.10.222:2181";//zk地址
            this.connectionTimeout =DEFAULT_CONNECT_TIMEOUT ;
            this.sessionTimeout =DEFAULT_SESSION_TIMEOUT;
            this.client =
                    CuratorFrameworkFactory.builder().connectString(connectionString)
                            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                            .connectionTimeoutMs(this.connectionTimeout)
                            .sessionTimeoutMs(this.sessionTimeout).namespace(rootPath).build();
            this.client.start();
            locks = new HashMap<String, InterProcessMutex>(32);
            batchlocks = new HashMap<String, InterProcessMultiLock>(32);
            System.out.println("初始化结束");
    	}
    	catch (Exception e) {
			System.out.println("初始化异常:"+e);
		}

    }

    /**
     *
     * <p>Descrption: 获取zk客户端</p>
     * @Author mac
     * @Datetime 2016年10月16日 下午4:07:20
     * @return CuratorFramework
     * @return
     */
    public CuratorFramework getClient() {
        return client;
    }

    /**
     *
     * <p>Descrption: 获取分布式锁</p>
     * @Author houxiaolong
     * @Datetime 2016年10月16日 下午4:07:43
     * @return boolean
     * @param action
     * @param lockId
     * @param time
     * @return
     * @throws Exception
     */
    public boolean lock(String action, String lockId, int time) throws Exception {
        String uniqueLockId = Strman.append(action, "_", lockId);
        InterProcessMutex lock = new InterProcessMutex(this.client, Strman.append("/", uniqueLockId));
        boolean isLocked = lock.acquire(time, TimeUnit.SECONDS);
        if (isLocked) {
            this.locks.put(uniqueLockId, lock);
        }
        return isLocked;
    }

    /**
     *
     * <p>Descrption: 获取分布式锁</p>
     * @Author houxiaolong
     * @Datetime 2016年10月16日 下午4:24:10
     * @return boolean
     * @param action
     * @param lockId
     * @return
     * @throws Exception
     */
    public boolean lock(String action, String lockId) throws Exception {
        return lock(action, lockId, DEFAULT_LOCK_TIMEOUT);
    }

    /**
     *
     * <p>Descrption: 释放锁</p>
     * @Author houxiaolong
     * @Datetime 2016年10月16日 下午4:24:28
     * @return void
     * @param action
     * @param lockId
     * @throws Exception
     */
    public void unlock(String action, String lockId) throws Exception {
        String uniqueLockId = Strman.append(action, "_", lockId);
        InterProcessMutex lock = null;
        if ((lock = this.locks.get(uniqueLockId)) != null) {
        	try {
        		this.locks.remove(uniqueLockId);
                lock.release();
                LOG.info("单独解锁成功："+uniqueLockId);
			} catch (Exception e) {
				LOG.error("单独解锁异常",e);
				throw e;
			}
        }
    }

    /**
     *
     * <p>Descrption: 批量锁</p>
     * @Author houxiaolong
     * @Datetime 2016年10月16日 下午6:40:34
     * @return boolean
     * @param action
     * @param lockIds
     * @param time
     * @return
     * @throws Exception
     */
    public boolean batchLock(String action, Collection<String> lockIds, int time) throws Exception {
        List<String> realLockIds = new ArrayList<String>();
        for (String lockId : lockIds) {
            String prefix = action.split("_")[0];
            realLockIds.add(Strman.append("/", prefix, "_", lockId));
        }
        InterProcessMultiLock mulLock = new InterProcessMultiLock(this.client, realLockIds);
        boolean isLocked = mulLock.acquire(time, TimeUnit.SECONDS);
        if (isLocked) {
            this.batchlocks.put(Strman.append(action, BATCH), mulLock);
        }
        return isLocked;
    }

    public boolean batchLock(String action, Collection<String> lockIds) throws Exception {
        return batchLock(action, lockIds, DEFAULT_LOCK_TIMEOUT);
    }

    public void unbatchLock(String action) throws Exception {
        String batchKey = Strman.append(action, BATCH);
        InterProcessMultiLock mulLock = null;
        if ((mulLock = this.batchlocks.get(batchKey)) != null) {
            try {
            	this.batchlocks.remove(batchKey);
                mulLock.release();
                LOG.info("批量解锁成功："+batchKey);
			} catch (Exception e) {
				LOG.error("批量解锁异常：",e);
				throw e;
			}
        }
    }


    /**
     *
     * <p>Descrption: 关闭客户端</p>
     * @Author houxiaolong
     * @Datetime 2016年10月16日 下午4:24:42
     * @return void
     */
    public void close() {
        if (this.client != null) {
            CloseableUtils.closeQuietly(this.client);
        }
    }
}
