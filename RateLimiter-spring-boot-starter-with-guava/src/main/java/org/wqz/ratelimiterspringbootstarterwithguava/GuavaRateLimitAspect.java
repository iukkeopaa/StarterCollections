package org.wqz.ratelimiterspringbootstarterwithguava;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class GuavaRateLimitAspect {

    @Around("@annotation(org.wqz.ratelimiterspringbootstarterwithguava.GuavaRateLimit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<GuavaRateLimit> GuavaRateLimit = null;
        GuavaRateLimit rateLimit = method.getAnnotation(GuavaRateLimit);

        RateLimiter rateLimiter = RateLimiter.create(rateLimit.permitsPerSecond());
        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}