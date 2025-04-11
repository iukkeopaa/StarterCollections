package org.wqz.ratelimiterspringbootstarterwithannotation;// RateLimitAspect.java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class RateLimitAspect {

    @Around("@annotation(org.wqz.ratelimiterspringbootstarterwithannotation.RateLimit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        RateLimiter rateLimiter;
        switch (rateLimit.strategy()) {
            case "fixedWindow":
                rateLimiter = new FixedWindowRateLimiter(rateLimit.limit(), rateLimit.windowSize());
                break;
            case "slidingWindow":
                rateLimiter = new SlidingWindowRateLimiter(rateLimit.limit(), rateLimit.windowSize());
                break;
            case "tokenBucket":
                rateLimiter = new TokenBucketRateLimiter(rateLimit.capacity(), rateLimit.rate());
                break;
            default:
                rateLimiter = new FixedWindowRateLimiter(rateLimit.limit(), rateLimit.windowSize());
        }

        if (rateLimiter.tryAcquire()) {
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("Rate limit exceeded");
        }
    }
}