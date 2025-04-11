package org.wqz.ratelimiterspringbootstarterwithannotation;

// RateLimiter.java
public interface RateLimiter {
    boolean tryAcquire();
}