package org.wqz.ratelimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSize;
    private final Queue<Long> timestamps;

    public SlidingWindowRateLimiter(int limit, long windowSize) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timestamps = new LinkedList<>();
    }

    @Override
    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > windowSize) {
            timestamps.poll();
        }
        if (timestamps.size() < limit) {
            timestamps.offer(currentTime);
            return true;
        }
        return false;
    }
}    