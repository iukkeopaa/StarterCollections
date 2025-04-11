package org.wqz.arthaslikestarter;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MethodWatchAspect {
    private static final Logger logger = LoggerFactory.getLogger(MethodWatchAspect.class);

    @Pointcut("execution(* org.wqz.arthaslikestarter.service.*.*(..))")
    public void methodPointcut() {}

    @Before("methodPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("Method {} is about to execute with args: {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @After("methodPointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        logger.info("Method {} has finished execution", joinPoint.getSignature().getName());
    }
}    