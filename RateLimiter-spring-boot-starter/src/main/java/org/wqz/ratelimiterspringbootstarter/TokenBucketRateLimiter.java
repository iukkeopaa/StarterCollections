package org.wqz.ratelimiterspringbootstarter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int rate;
    private int tokens;
    private final ScheduledExecutorService scheduler;

    public TokenBucketRateLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = capacity;
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::refill, 1, 1, TimeUnit.SECONDS);
    }

    private synchronized void refill() {
        tokens = Math.min(capacity, tokens + rate);
    }

    @Override
    public synchronized boolean tryAcquire() {
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}    