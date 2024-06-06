package com.vianny.dverivariant.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void updateData(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

