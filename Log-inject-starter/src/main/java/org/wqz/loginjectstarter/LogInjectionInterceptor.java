package org.wqz.loginjectstarter;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class LogInjectionInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInjectionInterceptor.class);

    @RuntimeType
    public Object intercept(@Origin Method method, @SuperCall Callable<Object> callable) throws Exception {
        logger.info("Entering method: {}", method.getName());
        try {
            return callable.call();
        } finally {
            logger.info("Exiting method: {}", method.getName());
        }
    }
}
    