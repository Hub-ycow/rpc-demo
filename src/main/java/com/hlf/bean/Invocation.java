package com.hlf.bean;

import java.io.Serializable;

public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class<?>[] paramsTypes;
    private Object[] params;
    private String version;

    public Invocation(String interfaceName, String methodName, Class<?>[] paramsTypes, Object[] params, String version) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramsTypes = paramsTypes;
        this.params = params;
        this.version = version;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamsTypes() {
        return paramsTypes;
    }

    public void setParamsTypes(Class<?>[] paramsTypes) {
        this.paramsTypes = paramsTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
