package com.hlf.provider.registry;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 注册中心
 */
public class LocalRegistry {
    private static final Map<String, List<URL>> serviceMap = new HashMap<>();


    public static void register(String interfaceName, List<URL> urls) {
        serviceMap.put(interfaceName, urls);
    }

    public static List<URL> get(String interfaceName) {
        return serviceMap.get(interfaceName);
    }

    public static URL random(List<URL> urlList) {
        Random random = new Random();
        return urlList.get(random.nextInt(urlList.size()));
    }
}
