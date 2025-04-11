package org.wqz.redistemplatestarter;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisTemplateWrapper {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTemplateWrapper(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            System.err.println("设置 Redis 键值对时出错: " + e.getMessage());
        }
    }

    /**
     * 设置键值对并指定过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            System.err.println("设置 Redis 键值对并指定过期时间时出错: " + e.getMessage());
        }
    }

    /**
     * 获取键对应的值
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            System.err.println("获取 Redis 键值时出错: " + e.getMessage());
            return null;
        }
    }

    /**
     * 删除键
     * @param key 键
     * @return 是否删除成功
     */
    public boolean delete(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.delete(key));
        } catch (Exception e) {
            System.err.println("删除 Redis 键时出错: " + e.getMessage());
            return false;
        }
    }

    /**
     * 检查键是否存在
     * @param key 键
     * @return 是否存在
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            System.err.println("检查 Redis 键是否存在时出错: " + e.getMessage());
            return false;
        }
    }
}    