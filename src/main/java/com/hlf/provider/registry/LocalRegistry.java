package com.hlf.provider.registry;

import com.hlf.util.ZKUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 注册中心   第一种目前是采用文件的形式将map进行保存 相当于使用本地磁盘作为共有组件
 * 第二种是使用zookeeper作为注册中心
 */
public class LocalRegistry {
    private static final Map<String, List<URL>> serviceMap = new HashMap<>();
    private static final File file = new File("D:" + File.separator + "registry.txt");
    private static final Logger log = LoggerFactory.getLogger(LocalRegistry.class);

    static {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void register(String interfaceName, List<URL> urls) {
        serviceMap.put(interfaceName, urls);

        //使用本地磁盘讲数据缓存起来 简单用作注册中心 但是可扩展性不行
       /* try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(serviceMap);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        boolean isSuccess = ZKUtil.createPathIfNotExists(interfaceName, serviceMap);
        if (!isSuccess) {
            throw new RuntimeException("注册中心写入数据失败");
        }
    }

    public static List<String> get(String interfaceName) {
        /*Map<String, List<URL>> map = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            map = (Map<String, List<URL>>) ois.readObject();
        } catch (Exception e) {
            log.error("获取注册url失败------->{}", e.getMessage());
        }
        if (map == null) {
            return null;
        }*/
        try {
            String urlList = ZKUtil.readData(interfaceName);
            Map<String, List<String>> map = ZKUtil.OBJECT_MAPPER.readValue(urlList, Map.class);
            return map.get(interfaceName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static URL random(List<String> urlList) throws MalformedURLException {
        Random random = new Random();
        int index = random.nextInt(urlList.size());
        String urlStr = urlList.get(index);
        try {
            URL url = new URL(urlStr);
            return url;
        } catch (Exception ex) {
            throw new RuntimeException("url格式错误");
        }
    }
}
