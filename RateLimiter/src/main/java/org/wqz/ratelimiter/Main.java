package org.wqz.ratelimiter;

public class Main {
    public static void main(String[] args) {
        // 固定窗口限流器，每秒允许 10 次请求
        RateLimiter fixedWindowLimiter = new FixedWindowRateLimiter(10, 1000);
        System.out.println("Fixed Window: " + fixedWindowLimiter.tryAcquire());

        // 滑动窗口限流器，每秒允许 10 次请求
        RateLimiter slidingWindowLimiter = new SlidingWindowRateLimiter(10, 1000);
        System.out.println("Sliding Window: " + slidingWindowLimiter.tryAcquire());

        // 令牌桶限流器，容量为 100，每秒生成 10 个令牌
        RateLimiter tokenBucketLimiter = new TokenBucketRateLimiter(100, 10);
        System.out.println("Token Bucket: " + tokenBucketLimiter.tryAcquire());
    }
}    