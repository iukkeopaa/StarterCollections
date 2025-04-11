package org.wqz.ratelimiter;

public interface RateLimiter {
    /**
     * 尝试获取许可
     * @return 如果获取成功返回 true，否则返回 false
     */
    boolean tryAcquire();
}    