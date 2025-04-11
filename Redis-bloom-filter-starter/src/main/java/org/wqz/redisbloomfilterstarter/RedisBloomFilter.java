package org.wqz.redisbloomfilterstarter;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.BitSet;
import java.util.concurrent.TimeUnit;

public class RedisBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    private final RedisTemplate<String, Object> redisTemplate;
    private final long expectedInsertions;
    private final double falseProbability;
    private final long size;
    private final int hashCount;

    public RedisBloomFilter(RedisTemplate<String, Object> redisTemplate, long expectedInsertions, double falseProbability) {
        this.redisTemplate = redisTemplate;
        this.expectedInsertions = expectedInsertions;
        this.falseProbability = falseProbability;
        this.size = optimalNumOfBits(expectedInsertions, falseProbability);
        this.hashCount = optimalNumOfHashFunctions(expectedInsertions, size);
    }

    public void add(String key, String value) {
        int[] hashes = hash(value);
        for (int hash : hashes) {
            redisTemplate.opsForValue().setBit(key, hash % size, true);
        }
    }

    public boolean mightContain(String key, String value) {
        int[] hashes = hash(value);
        for (int hash : hashes) {
            if (!redisTemplate.opsForValue().getBit(key, hash % size)) {
                return false;
            }
        }
        return true;
    }

    private int[] hash(String value) {
        int[] result = new int[hashCount];
        for (int i = 0; i < hashCount; i++) {
            result[i] = hash(value, SEEDS[i]);
        }
        return result;
    }

    private int hash(String value, int seed) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = seed * hash + value.charAt(i);
        }
        return Math.abs(hash);
    }

    private long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }

    private int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }
}    