package org.wqz.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSize;
    private long startTime;
    private final AtomicInteger counter;

    public FixedWindowRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.startTime = System.currentTimeMillis();
        this.counter = new AtomicInteger(0);
    }

    @Override
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - startTime > windowSize) {
            startTime = currentTime;
            counter.set(0);
        }
        if (counter.get() < limit) {
            counter.incrementAndGet();
            return true;
        }
        return false;
    }
}    