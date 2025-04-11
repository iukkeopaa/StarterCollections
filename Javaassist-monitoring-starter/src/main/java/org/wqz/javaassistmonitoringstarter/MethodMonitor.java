package org.wqz.javaassistmonitoringstarter;

import java.lang.reflect.Method;

public class MethodMonitor {

    public void beforeMethod(Method method) {
        System.out.println("开始执行方法: " + method.getName());
    }

    public void afterMethod(Method method, long executionTime) {
        System.out.println("方法 " + method.getName() + " 执行完毕，耗时: " + executionTime + " 毫秒");
    }
}    