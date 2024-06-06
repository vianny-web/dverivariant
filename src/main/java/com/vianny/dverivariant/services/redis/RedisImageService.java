package com.vianny.dverivariant.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisImageService {

    private final RedisTemplate<String, byte[]> redisTemplate;

    @Autowired
    public RedisImageService(RedisTemplate<String, byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, byte[] value, long ttl, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    public void updateData(String key, byte[] value, long ttl, TimeUnit timeUnit) {
        redisTemplate.delete(key);
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public byte[] getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
