package com.hlf.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ZKUtil {
    private static final ZkClient zkClient = new ZkClient("49.233.245.196:2181");
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String PREFIX = "/";

    public static <T> boolean createPathIfNotExists(String name, T data) {
        String path = getPath(name);
        String newPath = PREFIX + path;
        if (zkClient.exists(newPath)) {
            return true;
        }
        try {
            String dataString = OBJECT_MAPPER.writeValueAsString(data);
            zkClient.create(newPath, dataString, CreateMode.PERSISTENT);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String readData(String name) {
        String path = getPath(name);
        String newPath = PREFIX + path;
        return zkClient.readData(newPath);
    }

    private static String getPath(String name) {
        String[] nodes = name.split("\\.");
        String path = nodes[nodes.length - 1];
        return path;
    }
}
