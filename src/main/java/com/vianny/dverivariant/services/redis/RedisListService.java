package com.vianny.dverivariant.services.redis;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisListService {
    @Value("${redis.timeout}")
    private long TTL;
    private final TimeUnit timeUnit = TimeUnit.HOURS;
    private final RedisTemplate<String, List<ProductBriefDTO>> redisListTemplate;

    @Autowired
    public RedisListService(RedisTemplate<String, List<ProductBriefDTO>> redisListTemplate) {
        this.redisListTemplate = redisListTemplate;
    }

    public void saveData(String key, List<ProductBriefDTO> list) {
        redisListTemplate.opsForValue().set(key, list, TTL, timeUnit);
    }

    public void deleteData(String key) {
        redisListTemplate.delete(key);
    }

    public List<ProductBriefDTO> getData(String key) {
        return redisListTemplate.opsForValue().get(key);
    }
}
