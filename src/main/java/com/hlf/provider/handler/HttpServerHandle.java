package com.hlf.provider.handler;

import com.hlf.bean.Invocation;
import com.hlf.provider.mapping.InterfaceMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class HttpServerHandle {
    private static final String VERSION = "Impl";
    private static final String VERSION_V2 = VERSION + "V2";

    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        //这边是进行数据的处理
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            if (null == invocation) {
                throw new RuntimeException("invocation is null");
            }
            Class<?> interfaceClass;
            String[] name = invocation.getInterfaceName().split("\\.");
            String className = name[name.length - 1];
            if ("v2".equals(invocation.getVersion().toLowerCase())) {
                interfaceClass = InterfaceMapping.get(className + VERSION_V2);
            } else {
                interfaceClass = InterfaceMapping.get(className + VERSION);
            }
            if (interfaceClass == null) {
                throw new RuntimeException("interfaceClass is null");
            }
            Method method = interfaceClass.getMethod(invocation.getMethodName(), invocation.getParamsTypes());
            Object result = method.invoke(interfaceClass.getConstructor().newInstance(), invocation.getParams());
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            ServletOutputStream outputStream = resp.getOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
                oos.writeObject(result);
                oos.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
