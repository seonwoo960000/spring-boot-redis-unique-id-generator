package com.example.redisuniquegenerator.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate redisTemplate;

    public void insert(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Long getSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForSet().pop(key);
    }
}
