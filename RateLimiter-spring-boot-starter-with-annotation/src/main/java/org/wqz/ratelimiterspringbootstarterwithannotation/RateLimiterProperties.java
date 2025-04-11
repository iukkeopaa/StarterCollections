package org.wqz.ratelimiterspringbootstarterwithannotation;// RateLimiterProperties.java
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimiterProperties {
    // 可添加默认配置属性，这里暂不添加
}