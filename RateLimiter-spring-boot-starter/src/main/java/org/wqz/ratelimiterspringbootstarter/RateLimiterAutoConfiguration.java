package org.wqz.ratelimiterspringbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RateLimiterProperties.class)
public class RateLimiterAutoConfiguration {

    private final RateLimiterProperties properties;

    public RateLimiterAutoConfiguration(RateLimiterProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public FixedWindowRateLimiter fixedWindowRateLimiter() {
        return new FixedWindowRateLimiter(properties.getFixedWindowLimit(), properties.getFixedWindowSize());
    }

    @Bean
    @ConditionalOnMissingBean
    public SlidingWindowRateLimiter slidingWindowRateLimiter() {
        return new SlidingWindowRateLimiter(properties.getSlidingWindowLimit(), properties.getSlidingWindowSize());
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenBucketRateLimiter tokenBucketRateLimiter() {
        return new TokenBucketRateLimiter(properties.getTokenBucketCapacity(), properties.getTokenBucketRate());
    }
}    