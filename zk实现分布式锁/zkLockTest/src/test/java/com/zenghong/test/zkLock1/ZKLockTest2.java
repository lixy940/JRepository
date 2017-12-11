package com.zenghong.test.zkLock1;

import com.lixy.test.zkLock2.ZKTread;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

/**
 * @desc:
 * @Author:li_shuai
 * @date:Create on 2017/11/28 17:58
 */
public class ZKLockTest2 {
    public static void main(String... args) {

        //zk集群的地址
        String ZKServers = "192.168.46.130:2181,192.168.46.131:2181,192.168.46.132:2181";
        /**
         * 创建会话
         * new SerializableSerializer() 创建序列化器接口，用来序列化和反序列化
         */
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());
        //创建持久化节点，只在第一次创建，存在就不想要执行
        String path = zkClient.create("/testLock1", "", CreateMode.PERSISTENT);
        //String path = "/testLock1";
        ZKTread t1=new ZKTread(zkClient,path,"myLock");
        ZKTread t2=new ZKTread(zkClient,path,"myLock");
        t1.start();
        t2.start();
    }
}
