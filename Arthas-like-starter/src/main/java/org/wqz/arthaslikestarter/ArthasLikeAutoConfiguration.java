package org.wqz.arthaslikestarter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ArthasLikeProperties.class)
@ConditionalOnProperty(prefix = "arthas-like", name = "enabled", havingValue = "true")
public class ArthasLikeAutoConfiguration {
    // 可以在这里进行更多的 Bean 配置
}    