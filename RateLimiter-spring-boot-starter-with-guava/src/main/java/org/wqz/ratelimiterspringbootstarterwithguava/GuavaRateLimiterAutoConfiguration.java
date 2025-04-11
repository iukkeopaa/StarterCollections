package org.wqz.ratelimiterspringbootstarterwithguava;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GuavaRateLimiterProperties.class)
public class GuavaRateLimiterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GuavaRateLimitAspect guavaRateLimitAspect() {
        return new GuavaRateLimitAspect();
    }
}