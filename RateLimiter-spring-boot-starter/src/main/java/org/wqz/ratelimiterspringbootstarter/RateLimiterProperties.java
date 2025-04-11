package org.wqz.ratelimiterspringbootstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rate-limiter")
public class RateLimiterProperties {
    private int fixedWindowLimit = 10;
    private long fixedWindowSize = 1000;
    private int slidingWindowLimit = 10;
    private long slidingWindowSize = 1000;
    private int tokenBucketCapacity = 100;
    private int tokenBucketRate = 10;

    // Getters and Setters
    public int getFixedWindowLimit() {
        return fixedWindowLimit;
    }

    public void setFixedWindowLimit(int fixedWindowLimit) {
        this.fixedWindowLimit = fixedWindowLimit;
    }

    public long getFixedWindowSize() {
        return fixedWindowSize;
    }

    public void setFixedWindowSize(long fixedWindowSize) {
        this.fixedWindowSize = fixedWindowSize;
    }

    public int getSlidingWindowLimit() {
        return slidingWindowLimit;
    }

    public void setSlidingWindowLimit(int slidingWindowLimit) {
        this.slidingWindowLimit = slidingWindowLimit;
    }

    public long getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(long slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public int getTokenBucketCapacity() {
        return tokenBucketCapacity;
    }

    public void setTokenBucketCapacity(int tokenBucketCapacity) {
        this.tokenBucketCapacity = tokenBucketCapacity;
    }

    public int getTokenBucketRate() {
        return tokenBucketRate;
    }

    public void setTokenBucketRate(int tokenBucketRate) {
        this.tokenBucketRate = tokenBucketRate;
    }
}    