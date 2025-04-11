package org.wqz.ratelimiterspringbootstarterwithguava;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "guava-rate-limiter")
public class GuavaRateLimiterProperties {
    // 可添加默认配置属性，这里暂不添加
}