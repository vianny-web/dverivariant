package com.vianny.dverivariant.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisImageService {
    @Value("${redis.timeout}")
    private long TTL;
    private final TimeUnit timeUnit = TimeUnit.MINUTES;
    private final RedisTemplate<String, byte[]> redisTemplate;

    @Autowired
    public RedisImageService(RedisTemplate<String, byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveData(String key, byte[] value) {
        redisTemplate.opsForValue().set(key, value, TTL, timeUnit);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    public byte[] getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
