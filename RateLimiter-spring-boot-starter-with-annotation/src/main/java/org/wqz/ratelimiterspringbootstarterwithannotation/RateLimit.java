package org.wqz.ratelimiterspringbootstarterwithannotation;// RateLimit.java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    // 限流策略，可取值 "fixedWindow", "slidingWindow", "tokenBucket"
    String strategy() default "fixedWindow";
    // 限流阈值
    int limit() default 10;
    // 时间窗口大小（毫秒），仅固定窗口和滑动窗口策略使用
    long windowSize() default 1000;
    // 令牌桶容量，仅令牌桶策略使用
    int capacity() default 100;
    // 令牌生成速率，仅令牌桶策略使用
    int rate() default 10;
}