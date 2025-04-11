package org.wqz.redisstreamstarter;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RedisStreamTemplate {

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisStreamProperties redisStreamProperties;
    private final StreamReceiver<String, MapRecord<String, String, String>> streamReceiver;

    public RedisStreamTemplate(RedisConnectionFactory redisConnectionFactory, RedisStreamProperties redisStreamProperties) {
        this.redisTemplate = new RedisTemplate<>();
        this.redisTemplate.setConnectionFactory(redisConnectionFactory);
        this.redisTemplate.afterPropertiesSet();
        this.redisStreamProperties = redisStreamProperties;
        this.streamReceiver = StreamReceiver.create((ReactiveRedisConnectionFactory) redisConnectionFactory);
    }

    public void sendMessage(Map<String, String> message) {
        redisTemplate.opsForStream().add(redisStreamProperties.getStreamKey(), message);
    }

    public List<MapRecord<String, String, String>> readMessages() {
        StreamOffset<String> streamOffset = StreamOffset.create(redisStreamProperties.getStreamKey(), ReadOffset.lastConsumed());
        StreamReadOptions readOptions = StreamReadOptions.empty().block(Duration.ofSeconds(1)).count(10);
        return null;
    }
}    