package org.wqz.hystrixspringbootstarter.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 超时属性
 * @Author: wjh
 * @Date: 2025/4/11 上午8:48
 */
@ConfigurationProperties(prefix = "timeout.circuit-breaker")
public class TimeoutCircuitBreakerProperties {

    private int timeoutInMilliseconds = 1000;

    public int getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    public void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }
}