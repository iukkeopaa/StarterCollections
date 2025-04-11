package org.wqz.methodextspringbootstarter;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomInterceptorAspect {
    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptorAspect.class);

    @Pointcut("@annotation(org.wqz.methodextspringbootstarter.CustomIntercept)")
    public void customInterceptPointcut() {
    }

    @Before("customInterceptPointcut()")
    public void before(JoinPoint joinPoint) {
        logger.info("Before method: {}", joinPoint.getSignature().getName());
    }

    @After("customInterceptPointcut()")
    public void after(JoinPoint joinPoint) {
        logger.info("After method: {}", joinPoint.getSignature().getName());
    }
}    