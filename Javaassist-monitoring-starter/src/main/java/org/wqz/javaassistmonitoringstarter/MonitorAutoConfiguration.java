package org.wqz.javaassistmonitoringstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MethodMonitor methodMonitor() {
        return new MethodMonitor();
    }

    @Bean
    @ConditionalOnMissingBean
    public MonitorPostProcessor monitorPostProcessor(MethodMonitor methodMonitor) {
        return new MonitorPostProcessor(methodMonitor);
    }
}    