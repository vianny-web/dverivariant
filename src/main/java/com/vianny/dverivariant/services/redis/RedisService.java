package com.vianny.dverivariant.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Value("${redis.timeout}")
    private long TTL;
    private final TimeUnit timeUnit = TimeUnit.MINUTES;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, TTL, timeUnit);
    }

    public void updateData(String key, Object value) {
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, value, TTL, timeUnit);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

