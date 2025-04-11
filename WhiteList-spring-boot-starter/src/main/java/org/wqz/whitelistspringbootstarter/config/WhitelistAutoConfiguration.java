package org.wqz.whitelistspringbootstarter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wqz.whitelistspringbootstarter.aop.WhitelistAspect;

/**
 * @Description: 白名单的自动配置类
 * @Author: wjh
 * @Date: 2025/4/11 上午8:19
 */

@Configuration
public class WhitelistAutoConfiguration {


    @Bean

    public WhitelistAspect whitelistAspect() {
        return new WhitelistAspect();
    }
}
