package org.wqz.redisbloomfilterstarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.bloom.filter")
public class RedisBloomFilterProperties {

    private long expectedInsertions = 1000;
    private double falseProbability = 0.01;

    public long getExpectedInsertions() {
        return expectedInsertions;
    }

    public void setExpectedInsertions(long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public double getFalseProbability() {
        return falseProbability;
    }

    public void setFalseProbability(double falseProbability) {
        this.falseProbability = falseProbability;
    }
}    