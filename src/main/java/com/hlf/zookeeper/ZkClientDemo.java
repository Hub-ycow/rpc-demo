package com.hlf.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZkClientDemo {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("49.233.245.196:2181");
        Object data = zkClient.readData("/incar");
        if (null != data) {
            System.out.println(data);
        } else {
            zkClient.create("/incar", "test", CreateMode.PERSISTENT);
        }
        zkClient.close();
    }
}
