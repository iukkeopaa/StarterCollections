package org.wqz.ratelimiterspringbootstarter;

public interface RateLimiter {
    boolean tryAcquire();
}    