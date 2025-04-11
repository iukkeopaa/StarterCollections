package org.wqz.whitelistspringbootstarter.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 自动配置类
 * @Author: wjh
 * @Date: 2025/4/11 上午8:27
 */

@Configuration
@ConditionalOnClass(WhiteListProperties.class)
@EnableConfigurationProperties(WhiteListProperties.class)
public class WhiteListAutoConfigure {

    private Logger logger = LoggerFactory.getLogger(WhiteListAutoConfigure.class);

    @Bean("whiteListConfig")
    @ConditionalOnMissingBean
    public String whiteListConfig(WhiteListProperties properties) {
        logger.error("从application.yml加载whiteList白名单列表: {}", JSON.toJSONString(properties));
        return properties.getUsers();
    }
}
