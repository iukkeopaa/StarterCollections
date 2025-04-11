package org.wqz.hystrixspringbootstarter.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wqz.hystrixspringbootstarter.property.TimeoutCircuitBreakerProperties;

/**
 * @Description: 实现类
 * @Author: wjh
 * @Date: 2025/4/11 上午8:49
 */
@Service
public class TimeoutCircuitBreakerService {

    @Autowired
    private TimeoutCircuitBreakerProperties properties;

    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "#{@timeoutCircuitBreakerProperties.timeoutInMilliseconds}")
    })
    public String execute() {
        // 模拟耗时操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Success";
    }

    public String fallback() {
        return "Fallback";
    }
}
