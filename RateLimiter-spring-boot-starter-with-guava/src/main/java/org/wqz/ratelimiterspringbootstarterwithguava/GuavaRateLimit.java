package org.wqz.ratelimiterspringbootstarterwithguava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GuavaRateLimit {
    // 每秒允许的请求数
    double permitsPerSecond() default 10.0;
}