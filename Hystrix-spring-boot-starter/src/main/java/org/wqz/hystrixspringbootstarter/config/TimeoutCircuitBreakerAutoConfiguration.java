package org.wqz.hystrixspringbootstarter.config;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wqz.hystrixspringbootstarter.property.TimeoutCircuitBreakerProperties;

/**
 * @Description: 自动配置类
 * @Author: wjh
 * @Date: 2025/4/11 上午8:49
 */

@Configuration
@EnableConfigurationProperties(TimeoutCircuitBreakerProperties.class)
public class TimeoutCircuitBreakerAutoConfiguration {
    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
}
