package com.hlf.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZkClientDemo {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("49.233.245.196:2181");
        if (!zkClient.exists("/incar")) {
            zkClient.create("/incar", "test", CreateMode.PERSISTENT);
        }
        Object data = zkClient.readData("/incar");
        if (null != data) {
            System.out.println(data);
        } else {
            throw new RuntimeException("zk client create data failed");
        }
        zkClient.close();
    }
}
