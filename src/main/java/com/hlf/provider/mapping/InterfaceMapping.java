package com.hlf.provider.mapping;

import java.util.HashMap;
import java.util.Map;

public class InterfaceMapping {
    private static final Map<String, Class<?>> interfaceMap = new HashMap<>();

    public static Class<?> get(String interfaceName) {
        return interfaceMap.get(interfaceName);
    }

    public static void put(String interfaceName, Class<?> clazz) {
        interfaceMap.put(interfaceName, clazz);
    }
}
