package org.wqz.bytebuddymonitorstarter;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MonitorInterceptor {
    @RuntimeType
    public Object intercept(@Origin Method method, @AllArguments Object[] args, @SuperCall Callable<Object> callable) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            return callable.call();
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("Method " + method.getName() + " took " + (endTime - startTime) + " ms");
        }
    }
}    