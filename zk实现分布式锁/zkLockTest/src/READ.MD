zk实现分布式锁的两个实例

zkLock2方式比较好用


　  1、PERSISTENT-持久化目录节点
　　客户端与Zookeeper断开连接后，该节点依旧存在
　　2、PERSISTENT_SEQUENTIAL-持久化顺序编号目录节点
　　客户端与Zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
　　3、EPHEMERAL-临时目录节点
　　客户端与Zookeeper断开连接后，该节点被删除
　　4、EPHEMERAL_SEQUENTIAL-临时顺序编号目录节点
　　客户端与Zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号