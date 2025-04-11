package org.wqz.methodextspringbootstarter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CustomInterceptorProperties.class)
public class CustomInterceptorAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "custom.interceptor", name = "enabled", havingValue = "true")
    public CustomInterceptorAspect customInterceptorAspect() {
        return new CustomInterceptorAspect();
    }
}    