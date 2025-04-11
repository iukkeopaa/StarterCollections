package org.wqz.redisstreamstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.stream.StreamReceiver;

@Configuration
@EnableConfigurationProperties(RedisStreamProperties.class)
public class RedisStreamAutoConfiguration {

    private final RedisConnectionFactory redisConnectionFactory;
    private final RedisStreamProperties redisStreamProperties;

    public RedisStreamAutoConfiguration(RedisConnectionFactory redisConnectionFactory, RedisStreamProperties redisStreamProperties) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisStreamProperties = redisStreamProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisStreamTemplate redisStreamTemplate() {
        return new RedisStreamTemplate(redisConnectionFactory, redisStreamProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public StreamOperations<String, String, String> streamOperations() {
        return (StreamOperations<String, String, String>) redisConnectionFactory.getConnection().streamCommands();
    }


}    